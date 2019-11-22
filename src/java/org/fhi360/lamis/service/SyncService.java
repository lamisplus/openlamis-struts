/**
 * @author user1
 */
package org.fhi360.lamis.service;

import org.apache.commons.lang3.StringUtils;
import org.fhi360.lamis.service.beans.ContextProvider;
import org.fhi360.lamis.utility.Constants;
import org.fhi360.lamis.utility.FileUtil;
import org.fhi360.lamis.utility.PropertyAccessor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.RandomStringUtils;

public class SyncService {
    private static ExecutorService executorService = Executors.newFixedThreadPool(30);
    private static final Set<String> runningFacilities = new HashSet<>();

    public SyncService() {

    }

    public void startTransaction() {
        //Check if all the facility folders has the lock.ser file
        //If not create a lock.ser file if there are xml files to be consumed
        //String[] dateRanges = getDateRanges().split(",");
        //for(String dateRange : dateRanges){
        String facilityIds = getFacilityIds();
        if (!facilityIds.isEmpty()) {
            sync(facilityIds);
        }
 
    }

    public void lockSyncFolder() {
        //Check if there is any sync folder that has xml files but not locked and lock it to avoid overwrite
        Map<String, Object> map = new PropertyAccessor().getSystemProperties();
        String contextPath = (String) map.get("contextPath");
        File file;

        UploadFolderService uploadFolderService = new UploadFolderService();
        try {
            //String[] dateRanges = getDateRanges().split(","); //REMOVE LATER...
            //for(String dateRange: dateRanges){
            String[] facilityIds = getFacilityIds().split(",");
            for (String facilityId : facilityIds) {
                if (new File(contextPath + "exchange/sync/" + facilityId).exists()) {
                    String folder = contextPath + "exchange/sync/" + facilityId + "/";
                    if (uploadFolderService.getUploadFolderStatus(folder).equalsIgnoreCase("unlocked")) {

                        //check if xml files exist
                        boolean found = true;
                        String[] tables = Constants.Tables.MINIMUM_TRANSACTION_TABLES.split(",");
                        for (String table : tables) {
                            System.out.println("Handling...Facility: " + facilityId + " started ->  " + table);
                            String fileName = folder + table + ".xml";
                            file = new File(fileName);
                            if (!file.exists()) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            uploadFolderService.lockUploadFolder(folder); //lock folder after writing xml files to database
                        }
                    }
                }
            }
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sync(String ids) {
        String[] facilityIds = ids.split(",");
        for (String facilityId : facilityIds) {
            if (StringUtils.isNumeric(facilityId)) {
                synchronized (runningFacilities) {
                    if (!runningFacilities.contains(facilityId)) {
                        SyncThread syncThread = new SyncThread(facilityId);
                        executorService.execute(syncThread);
                        runningFacilities.add(facilityId);
                    }
                }
            }
        }
    }


    private String getDateRanges() {
        Map<String, Object> map = new PropertyAccessor().getSystemProperties();
        String contextPath = (String) map.get("contextPath");
        File directory = new File(contextPath + "exchange/sync/");

        String dateRanges = "";
        if (!directory.exists()) {
            System.out.println("Directory does not exist.");
        } else {
            try {
                String[] files = directory.list();
                if (files != null) {
                    for (String dateRange : files) {
                        dateRanges = dateRanges.isEmpty() ? dateRange : dateRanges + "," + dateRange;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Date Ranges are: " + dateRanges);
        return dateRanges;
    }

    private String getFacilityIds() {
        Map<String, Object> map = new PropertyAccessor().getSystemProperties();
        String contextPath = (String) map.get("contextPath");
        File directory = new File(contextPath + "exchange/sync/");

        String facilityIds = "";
        if (!directory.exists()) {
            System.out.println("Directory does not exist.");
        } else {
            try {
                String[] files = directory.list();
                if (files != null) {
                    facilityIds = String.join(",", files);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return facilityIds;
    }

    private long getFacilityCount() {
        return ContextProvider.getBean(JdbcTemplate.class)
                .queryForObject("SELECT MAX(facility_id) AS max_count FROM facility", Long.class);
    }

    class SyncThread implements Runnable {
        private String facilityId;

        SyncThread(String facilityId) {
            this.facilityId = facilityId;
        }

        @Override
        public void run() {
            System.out.println(String.format("Sync for facility %s started at: %s", facilityId, new Date()));
            Map<String, Object> map = new PropertyAccessor().getSystemProperties();
            String contextPath = (String) map.get("contextPath");
            File file;
            UploadFolderService uploadFolderService = new UploadFolderService();
            FileUtil fileUtil = new FileUtil();
            XmlParserDelegator xmlParserDelegator = new XmlParserDelegator();
            EntityIdentifier eId = new EntityIdentifier();
            try {
                String folder = contextPath + "exchange/sync/" + facilityId + "/";
                //If the folder is locked by the webservice, process the content of the folder
                if (uploadFolderService.getUploadFolderStatus(Long.parseLong(facilityId)).equalsIgnoreCase("locked")) {
                    //Check for process lock file, if not available create else skip
                    System.out.println("Process Folder Status for facility ID:" + facilityId + " is: " + uploadFolderService.getUploadFolderSyncingStatus(Long.parseLong(facilityId)));
                    if (uploadFolderService.getUploadFolderSyncingStatus(Long.parseLong(facilityId)).equalsIgnoreCase("unlocked")) {
                        uploadFolderService.lockUploadFolderSyncing(folder);
                        String[] tables = Constants.Tables.TRANSACTION_TABLES.split(",");

                        for (String table : tables) {
                            String fileName = folder + table + ".xml";
                            file = new File(fileName);
                            if (file.exists()) {                                
                                System.out.println("Starting file inflation at: " + new Date());
                                fileUtil.inflateFile(fileName, facilityId);  
                                System.out.println("Finishing file inflation at: " + new Date());
                                xmlParserDelegator.delegate(table, fileName);
                                System.out.println("Done..." + file);
                                file.delete();
                            }
                        }
                        File fileToDelete = new File(contextPath + "exchange/sync/" + facilityId);
                        fileUtil.deleteFile(fileToDelete);

                        /*
                        if (fileToDelete.listFiles() != null) {
                            if (fileToDelete.listFiles().length == 2) {
                                fileUtil.deleteFile(fileToDelete);
                            } else {
                                //Unlock the process.ser...
                                File processFile = new File(contextPath + "exchange/sync/" + facilityId + "/process.ser");
                                fileUtil.deleteFile(processFile);
                                System.out.println("Folder still contains files: " + fileToDelete.listFiles().length);
                            }
                        }
						*/
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            
            synchronized (runningFacilities) {
                runningFacilities.remove(facilityId);
            }
        }
    }
}
