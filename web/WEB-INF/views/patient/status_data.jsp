<%-- 
    Document   : Status
    Created on : Feb 8, 2012, 1:15:46 PM
    Author     : AALOZIE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>LAMIS 3.0</title>
        <jsp:include page="/WEB-INF/views/template/css.jsp" /> 
        <jsp:include page="/WEB-INF/views/template/javascript.jsp" /> 
        <script type="text/javascript" src="js/jquery.maskedinput-1.3.min.js"></script>
        <script type="text/javascript" src="js/lamis/lamis-common.js"></script>               
        <script type="text/javascript" src="js/lamis/status-common.js"></script>               
        <script type="text/javascript" src="js/lamis/report-common.js"></script>               
        <script type="text/JavaScript"> 
            var lastSelectDate = "";
            var updateRecord = false;
          
         
            function enableControl(){
                var outcome = $("#outcome").val();
                if (outcome == "ART Restart" || outcome == "ART Transfer Out" || outcome == "Pre-ART Transfer Out" || outcome == "Lost to Follow Up") {
                    $("#date").removeAttr("disabled");

                    $("#date1").attr("disabled", "disabled");
                    $("#date2").attr("disabled", "disabled");
                    $("#reasonInterrupt").attr("disabled", "disabled");
                    $("#causeDeath").attr("disabled", "disabled");
                }
                if (outcome == "Stopped Treatment") {
                    $("#date").removeAttr("disabled");
                    $("#reasonInterrupt").removeAttr("disabled");

                    $("#date1").attr("disabled", "disabled");
                    $("#date2").attr("disabled", "disabled");
                    $("#causeDeath").attr("disabled", "disabled");
                }
                if (outcome == "Died (Confirmed)") {
                    $("#date").removeAttr("disabled");
                    $("#causeDeath").removeAttr("disabled");

                    $("#date1").attr("disabled", "disabled");
                    $("#date2").attr("disabled", "disabled");
                    $("#reasonInterrupt").attr("disabled", "disabled");
                }
                if (outcome == "Previously Undocumented Patient Transfer (Confirmed)" || outcome == "Traced Patient (Unable to locate)") {
                    $("#date1").removeAttr("disabled");

                    $("#date2").attr("disabled", "disabled");
                    $("#date").attr("disabled", "disabled");
                    $("#reasonInterrupt").attr("disabled", "disabled");
                    $("#causeDeath").attr("disabled", "disabled");
                }
                if (outcome == "Traced Patient and agreed to return to care") {
                    $("#date1").removeAttr("disabled");
                    $("#date2").removeAttr("disabled");

                    $("#date").attr("disabled", "disabled");
                    $("#reasonInterrupt").attr("disabled", "disabled");
                    $("#causeDeath").attr("disabled", "disabled");
                }
            }
            $(document).ready(function(){
                resetPage();
                initialize();
                reports();
                
//               if ($("#date").val() == '')
//                   $("#save_button").attr("disabled","disabled");
//               $("#date").change(function(){
//                    if ($("#date").val() == '')
//                        $("#save_button").attr("disabled","disabled");
//                    else
//                        $("#save_button").removeAttr("disabled");
//               });
//                   

              
                $.ajax({
                    url: "Patient_retrieve.action", 
                    dataType: "json",             
                    success: function(patientList) { 
                        // set patient id and number for which infor is to be entered
                        
                        for (var i = 0; i < patientList.length; i++) {
                                    $("#patientId").val(patientList[i].patientId);
                                    $("#hospitalNum").val(patientList[i].hospitalNum);
                                    $("#previousStatus").html(patientList[i].currentStatus);
                                    $("#currentStatus").html(patientList[i].currentStatus);                                    
                                    $("#patientInfor").html(patientList[i].surname + " " + patientList[i].otherNames);
                                    
                                    date = patientList[i].dateCurrentStatus;
                                    $("#currentStatus").val(patientList[i].currentStatus);
                                    $("#datePreviousStatus").html(date.slice(3, 5) + "/" + date.slice(0, 2) + "/" + date.slice(6));
                                    $("#lastStatusDate").val(date.slice(3,5)+"/"+date.slice(0,2)+"/"+date.slice(6));
                                    $("#dateCurrentStatus").val(dateSlice(date));
                                } 
                    },
                    error:  function(err){
                        console.log(err);
                    }
                }); //end of ajax call
 
 
                $.ajax({
                    url: "Status_retrieve.action",
                    dataType: "json",   
                    data: {historyId: sessionStorage.getItem("historyId"), dateCurrentStatus: sessionStorage.getItem("dateCurrentStatus")},
                    success: function(statusList) {
                        console.log("Status History data", statusList);
                       populateForm(statusList); 
                    },
                    error: function(err){
                        console.log(err);
                    }
                }); //end of ajax call
                
                 
        }); 
        
        function populateForm(statusList){
                console.log('Status list', statusList)
                for (var i = 0; i < statusList.length; i++) {
                       //if (statusList[i].historyId == sessionStorage.getItem("historyId")){
                               
                                if (statusList[i].currentStatus == "Known Death")
                                    $("#outcome").val("Died (Confirmed)");
                                else 
                                     $("#outcome").val(statusList[i].currentStatus);
                                 
                                date = statusList[i].dateCurrentStatus;  
                                $("#outcomeDate").val(dateSlice(date)); 
                                $("#historyId").val(statusList[i].historyId);                         
                                $("#reasonInterrupt").val(statusList[i].reasonInterrupt);  
                                $("#causeDeath").val(statusList[i].causeDeath); 
                                $("#agreedDate").val(statusList[i].agreedDate); 
                                
                                $("#date").val(dateSlice(date));
                                updateRecord = true;
                                lastSelected = statusList[i].historyId;
                                initButtonsForModify();
                                enableControl();
                                
                               if(statusList[i].deletable == "1"){
                                    $("#currentStatus").val(statusList[i].currentStatus);
                                    $("#dateCurrentStatus").val(dateSlice(date));
                                
                                    $("#delete_button").removeAttr("disabled");
                                    $("#save_button").removeAttr("disabled");
                                } else {
                                    $("#delete_button").attr("disabled", "disabled");
                                    $("#save_button").attr("disabled", "disabled");

                                }
                     
                }  
            
        }
       
        </script>
    </head>

    <body>
      <div class="wrapper">
            <jsp:include page="/WEB-INF/views/template/header.jsp" />
            <jsp:include page="/WEB-INF/views/template/nav_casemanagement.jsp"/>
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="Home_page.action">Home</a></li>
                    <li class="breadcrumb-item"><a href="Casemanagement_page.action">Case Management</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Client Status Update</li>
                </ol>
            </nav>        
            <div class="row">
                <div class="col-md-8 ml-auto mr-auto">
                    <div class="card">
                        <s:form id="lamisform" theme="css_xhtml">
                                <div id="messageBar"></div>
                                 <div class="card-body">
                                     
                                     <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Hospital No:</label>
                                        <input name="hospitalNum" type="text" class="form-control" id="hospitalNum"
                                               readonly="readonly" />
                                        <input name="name" type="hidden" id="name" />
                                        <input name="patientId" type="hidden"  id="patientId" />
                                      
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <p><br clear="both">
                                            <span id="patientInfor" class="text-dark title"></span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                                     
                                     
                                     
                            <input name="historyId" type="hidden" id="historyId"/>
                             <input name="currentStatus" type="hidden" id="currentStatus"/>
                                    <input name="dateCurrentStatus" type="hidden" id="dateCurrentStatus"/>
                                    <input name="lastStatusDate" type="hidden" id="lastStatusDate" />
                                     <input name="gender" type="hidden" id="gender"/>
                                    <input name="dateBirth" type="hidden" id="dateBirth"/>
                                <div class="row">
                                    <div class="col-md-6 form-group">
                                    <label>Current Status: </label>
                                </div>
                                <div class="col-md-6 form-group">
                                        <div class="col-md-4">
                                    <span id="previousStatus" style="color:blue"></span>
                                </div>
                                </div>
                                </div>
                                   
                                <div class="row">
                                      <div class="col-md-6 form-group">
                                    <label>Date of Current Status: </label>
                                </div>
                              <div class="col-md-6 form-group">
                                        <div class="col-md-4">
                                    <span id="datePreviousStatus" style="color:blue"></span>
                                </div>
                            </div>                            
                               </div>
                                    
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="form">New Status:</label>
                                       <select name="outcome" style="width: 100%" class="form-control select2" id="outcome">
                                        <option ></option>
                                        <option>ART Restart</option>
                                        <option>ART Transfer Out</option>
                                        <option>Pre-ART Transfer Out</option>
                                        <option>Lost to Follow Up</option>
                                        <option>Stopped Treatment</option> 
                                        <option>Died (Confirmed)</option>
                                        <option>Previously Undocumented Patient Transfer (Confirmed)</option>
                                        <option>Traced Patient (Unable to locate)</option>
                                        <option>Traced Patient and agreed to return to care</option>
                                        <option>Did Not Attempt to Trace Patient</option>
                                    </select><span id="statusregHelp" class="errorspan"></span>
                                </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                        <labe class="form-label">Date of New Status:</label>
                                <div id="statusDateTd2"><input name="date" type="text"  class="form-control" id="date"/>
                                    <input name="outcomeDate" type="hidden" id="outcomeDate"/><span id="dateHelp" class="errorspan"></span>
                                </div>
                            </div>
                                </div>
                                </div>
                                    <div  class="row">
                                        <div class="col-md-6">
                                    <div class="form-group">
                                         <div id="trackedDateTr" >
                                        <label class="form-label">Date of Tracked:</label>
                                    <input name="date1" type="text"  class="form-control" id="date1" disabled/>
                                    <input name="dateTracked" type="hidden" id="dateTracked" /><span id="dateHelp" class="errorspan"></span>
                                </div>
                                </div>
                                        </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="form-label">Date Agreed to Return:</label>
                                    <input name="date2" type="text" class="form-control" id="date2" disabled/>
                                    <input name="agreedDate" type="hidden" id="agreedDate" /><span id="dateHelp" class="errorspan"></span>
                                </div>
                            </div>
                                    </div>
                                        <div class="row">
                                                  <div class="col-md-6">
                                            <div class="form-group">
                                            <div id="deathTr">
                                        <label class="form-label">Cause of Death:</label>
                                    <select name="causeDeath" style="width: 100%" class="form-control select2" id="causeDeath" disabled>
                                        <option></option>
                                        <option>HIV disease resulting in TB</option>
                                        <option>HIV disease resulting in cancer</option>
                                        <option>HIV disease resulting in other infectious and parasitic disease</option>
                                        <option>Other HIV disease resulting in other disease or conditions leading to death</option>
                                        <option>Other natural causes</option>
                                        <option>Non-natural causes</option>
                                        <option>Unknown cause</option>
                                    </select>
                                </div>
                                 </div>
                                            </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="form-label">Reason for Interruption:</label>
                                        <select name="reasonInterrupt" style="width: 100%" class="form-control select2" id="reasonInterrupt" disabled>
                                        <option></option>
                                        <option>Toxicity/side effect</option>
                                        <option>Pregnancy</option>
                                        <option>Treatment failure</option>
                                        <option>Poor adherence</option>
                                        <option>Illness, hospitalization</option>
                                        <option>Drug out of stock</option>
                                        <option>Patient lacks finances</option>
                                        <option>Other patient decision</option>
                                        <option>Planned Rx interruption</option>
                                        <option>Other</option>
                                    </select>
                                </div>
                            </div>
                            </div>
                                        </div>
 
                            <div id="userGroup" style="display: none">
                                <s:property value="#session.userGroup" />
                            </div>
                            <div id="buttons" style="width: 200px">
                                <div id="userGroup" style="display: none">
                                    <s:property value="#session.userGroup" />
                                </div>
                            </div>
                                    <div class="pull-right">
                                        <button id="save_button" class="btn btn-fill btn-info">Save</button>
                                        <button id="close_button" class="btn btn-fill btn-default">Close</button>
                                    </div>

                                
                            </div>
                        </s:form>
                        <div id="user_group" style="display: none">Patients</div>
                    </div>
                </div>
        <jsp:include page="/WEB-INF/views/template/footer.jsp" />
    </body>
</html>