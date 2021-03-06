<%-- 
    Document   : reassign_client
    Created on : Oct 20, 2017, 3:30:16 PM
    Author     : user10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>LAMIS 3.0</title>
        <!-- <link type="text/css" rel="stylesheet" href="css/lamis.css" /> -->
        <jsp:include page="/WEB-INF/views/template/css.jsp" /> 
        <jsp:include page="/WEB-INF/views/template/javascript.jsp" /> 
        <script type="text/JavaScript">
            var enablePadding = true;
            var patientIds = [];
            var selectedIds;
            var caseManagerId;
            var done = false;
            $(document).ready(function(){
                resetPage();
                initializeClients();
                reports();
                $("#detailstd").hide();
                $("#detailstdRe").hide();
                
                $("#loader").html('<img id="loader_image" src="images/loader_small.gif" />');
                $("#reassign_button").prop("disabled", true); 

                $(".search").on("keyup", function(){
                    var value = $(this).val();
                    var casemanagerId = $("#casemanagerId").val();
                    $("#grid").setGridParam({url: "Casemanager_client_search.action?q=1&casemanagerId="+casemanagerId+"&value="+value, page:1}).trigger("reloadGrid");
                });
                
                $.ajax({
                    url: "Init_client.action",
                    dataType: "json",
                    success: function(clientsMap) {
                        $("#grid").jqGrid("clearGridData", true).setGridParam({url: "Casemanagerclients_grid.action?casemanagerId="+$("#casemanagerId").val(), page:1}).trigger("reloadGrid");
                        $("#loader").html(''); 
                        done = true;
                    }                    
                }); 
 
                $("#dialog").dialog({
                    title: "Client to Case Manager Re-Assignment",
                    autoOpen: false,
                    width: 500,
                    resizable: false,
                    buttons: [{text: "Yes", click: reAssignClients}, 
                        {text: "No", click: function(){$(this).dialog("close")}}]
                });
                
                $.ajax({
                    url: "Casemanager_retrieve.action",
                    dataType: "json",
                    success: function(caseManagerMap) {
                        var options = "<option value = '" + '0' + "'>" + '' + "</option>";
                        $.each(caseManagerMap, function(key, value) {
                            options += "<option value = '" + key + "'>" + value + "</option>";
                        }) //end each
                        $("#casemanagerId").html(options);                        
                    }                    
                }); //end of ajax call

                $("#casemanagerId").change(function(event){
                    var casemanagerId = $("#casemanagerId").val();
                    $("#reassign_button").prop("disabled", true);
                    if(casemanagerId != 0){
                        //First Ajax Call...
                        $.ajax({ //retrieves other case managers...
                            url: "Casemanagerassign_retrieve.action", 
                            dataType: "json",
                            data: {casemanagerId: casemanagerId},
                            success: function(caseManagerExceptMap) {
                                var options = "<option value = '" + '0' + "'>" + '' + "</option>";
                                $.each(caseManagerExceptMap, function(key, value) {
                                    options += "<option value = '" + key + "'>" + value + "</option>";
                                }) //end each
                                $("#newcasemanagerId").html(options);                       
                            }                    
                        }); //end of ajax call
                        
                        //Populate the clients for the selected case manager...             
                        $("#grid").jqGrid("clearGridData", true).setGridParam({url: "Casemanagerclients_grid.action?casemanagerId="+casemanagerId, page:1}).trigger("reloadGrid");     
                        
                        getCaseManagerDetails(casemanagerId, "assign");
                        $("#detailstd").show();   
                        $("#detailstdRe").hide();
                    }else{
                        //Re-opulate the clients for the selected case manager...             
                        $("#grid").jqGrid("clearGridData", true).setGridParam({url: "Casemanagerclients_grid.action?casemanagerId="+casemanagerId, page:1}).trigger("reloadGrid");
                        $("#newcasemanagerId").empty();
                        $("#detailstd").hide();
                        $("#detailstdRe").hide();
                    }
                });
                
                $("#newcasemanagerId").bind("change", function(event){
                    var casemanagerId = $("#newcasemanagerId").val();
                    if(casemanagerId != "0"){ 
                        $("#reassign_button").prop("disabled", false);
                        //console.log("Has "+$("#caseManagerAssignments").html());
                        if($("#caseManagerAssignments").html() > 0){                          
                            //$("#reassign_button").prop("disabled", false);
                        }
                        getCaseManagerDetails(casemanagerId, "re-assign");
                        $("#detailstdRe").show();
                    }
                    else if(casemanagerId == "0"){
                        //console.log("Has not "+$("#caseManagerAssignments").html())
                        $("#reassign_button").prop("disabled", true);
                        $("#detailstdRe").hide();
                    }
                });

                var lastSelected = -99;
                $("#grid").jqGrid({
                    //url: "Assign_CaseManager_grid.action",
                    url: "Casemanagerclients_grid.action?casemanagerId="+$("#casemanagerId").val(),
                    datatype: "json",
                    mtype: "GET",
                    colNames: ["Hospital No", "Name", "Gender", "Date of Birth", "Address", "ART Status"],
                    colModel: [
                        //{name: "sel", index: "sel", width: "50", align: "center", formatter:"checkbox", editoptions:{value:"1:0"}, formatoptions:{disabled:false}},                        
                        {name: "hospitalNum", index: "hospitalNum", width: "120"},
                        {name: "name", index: "name", width: "200"},
                        {name: "gender", index: "gender", width: "150"},
                        {name: "dateBirth", index: "dateBirth", width: "150", formatter: "date", formatoptions: {srcformat: "m/d/Y", newformat: "d-M-Y"}},                        
                        {name: "address", index: "address", width: "390"},                        
                        {name: "currentStatus", index: "currentStatus", width: "150"},

                    ],
                    pager: $('#pager'),
                    rowNum: 100,
                    sortname: "hospitalNum",
                    sortorder: "desc",
                    viewrecords: true,
                    multiselect: true,
                    imgpath: "themes/basic/images",
                    resizable: false,
                    height: 250,                    
                    jsonReader: {
                        root: "caseManagerClientsList",
                        page: "currpage",
                        total: "totalpages",
                        records: "totalrecords",
                        repeatitems: false,
                        id: "patientId"
                    }, 
                    onSelectRow: function(id) {
                        var data = $(this).getRowData(id);
                        if(data.sel == 1) {
                            $("#grid").jqGrid('setRowData', id, false, {background: 'khaki'});
                        }
                        else {
                            $("#grid").jqGrid('setRowData', id, false, {background: 'khaki'});
                        }                        
                    }
                });
                
                $("#reassign_button").bind("click", function(event){
                    if($("#userGroup").html() != "Administrator") {
                        $("#lamisform").attr("action", "Error_message");
                        return true;                        
                    }
                    else {
                        var casemanagerId = $("#newcasemanagerId").val();
                        selectedIds = jQuery("#grid").jqGrid('getGridParam','selarrrow');                       
                        if(selectedIds == ""){
                            alert("Please mark clients to de-assign");
                        }
                        else{
                            patientIds = selectedIds;
                            caseManagerId = casemanagerId;
                            $("#dialog").dialog("open");
                        }
                        event.preventDefault();
                        return false;                        
                    }
                });

                $("#close_button").bind("click", function(event){
                    $("#lamisform").attr("action", "Casemanagement_page");
                    return true;
                });
            });
            
            function getCaseManagerDetails(casemanagerId, option) {
                $.ajax({
                    url: "Casemanagerdetails_retrieve.action?casemanagerId="+casemanagerId,
                    dataType: "json",
                    success: function(caseManagerDetailsMap) {
                        console.log(caseManagerDetailsMap)
                        if(option.includes("re")){
                            //$("#caseManagerReligionRe").html(caseManagerDetailsMap.religion);
                            $("#caseManagerGenderRe").html(caseManagerDetailsMap.sex.toUpperCase());                
                            $("#caseManagerAssignmentsRe").html(caseManagerDetailsMap.clientCount);
                        }else{
                            //$("#caseManagerReligion").html(caseManagerDetailsMap.religion);
                            $("#caseManagerGender").html(caseManagerDetailsMap.sex.toUpperCase());     
                            $("#caseManagerAssignments").html(caseManagerDetailsMap.clientCount);
                        }
                        
                    }                    
                }); //end of ajax call
            }
            
            function reAssignClients() {  
                $("#dialog").dialog("close");
                var data = {"patient_ids" : selectedIds.toString(), "casemanagerId" : caseManagerId.toString()};
                $.ajax({
                    url: "Casemanager_reassign.action",
                    data: data,
                    dataType: "json",
                    success: function(assignmentMap) {
                       if(assignmentMap.response.includes("success")){
                           $("#loader").html('<img id="loader_image" src="images/loader_small.gif" />');
                           $("#grid").setGridParam({url: "Casemanagerclients_grid.action?casemanagerId="+$("#casemanagerId").val(), page:1}).trigger("reloadGrid");
                           $("#loader").html('');
                           $("#casemanagerId").val('');
                           $("#newcasemanagerId").val('');
                           $("#detailstd").hide();
                           $("#detailstdRe").hide();
                        }
                    }
                });
            }                
           
        </script>
    </head>

    <body>
        <jsp:include page="/WEB-INF/views/template/header.jsp" /> 
        <jsp:include page="/WEB-INF/views/template/nav_casemanagement.jsp" /> 
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="Home_page.action">Home</a></li>
                <li class="breadcrumb-item"><a href="Casemanagement_page">Case Management</a></li>
                <li class="breadcrumb-item active" aria-current="page">Re-Assign Clients To Case Managers</li>
            </ol>
        </nav>         
        <div class="row">
            <div class="col-md-10 ml-auto mr-auto">
                <div class="card">
                    <s:form id="lamisform" theme="css_xhtml">
                        <div id="loader"></div>
                        <div id="messageBar"></div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="form-label">De-Assign From:</label>
                                        <select name="casemanagerId"class="form-control select2" style="width: 100%;" id="casemanagerId">
                                            <option></option>
                                        </select><span id="caseManagerHelp" class="errorspan"></span>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="form-label">Assign To:</label>
                                        <select name="newcasemanagerId" class="form-control select2" style="width: 100%;" id="newcasemanagerId">
                                        </select><span id="newcaseManagerHelp" class="errorspan"></span>
                                    </div>
                                </div>
                            </div>
                            <td id="detailstd" style="display: none">
                                <!--<label><strong id="caseManagerReligion">Christianity</strong> : </label>-->  
                                <label>Gender:<strong id="caseManagerGender"></strong> : </label>
                                <label><strong id="caseManagerAssignments">0</strong> Clients(s) Assigned </label>
                            </td>
                            <td></td>
                            <td id="detailstdRe" style="display: none">

                                <!--<label><strong id="caseManagerReligionRe">Christianity</strong> : </label>-->  
                                <label>Gender: <strong id="caseManagerGenderRe"></strong> : </label>
                                <label><strong id="caseManagerAssignmentsRe">0</strong> Clients(s) Assigned </label>
                            </td>
                            </tr>
                            </table>					
                            <p></p>                            
                            <div>
                                <div> 
                                    <!--<div class="btn-group pull-right">
                                    <button id="reassign_button" class="btn btn-fill btn-info" disabled>Re-Assign</button>--> 
                                </div>


                                <div class="input-group no-border col-md-3 pull-right">
                                    <input type="text" class="form-control search" placeholder="search...">
                                    <div class="input-group-append">
                                        <div class="input-group-text">
                                            <i class="now-ui-icons ui-1_zoom-bold"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-1 col-sm-offset-11">
                                        <button type="button" id="reassign_button" class="btn btn-sm btn-info">Re-Assign</button>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                    <table id="grid"  class="table table-striped table-bordered center"></table>
                                    <div id="pager" style="text-align:center;"></div>
                                    </td>
                                    </tr>
                                    </table>
                                </div>
                                <p></p>
                                <div>  
                                    <div id="dialog">
                                        <tr>
                                            <td><label>Do you want to continue with case manager assignment for select clients?</label></td>
                                        </tr>
                                        <tr>
                                            <td width="20%"><label>Click Yes to continue or No to cancel:</label></td>
                                        </tr>   
                                        </table>
                                    </div>
                                    <div id="userGroup" style="display: none"><s:property value="#session.userGroup"/></div>
                                </div>                        
                            </s:form>
                        </div>
                    </div>
                </div>

                <div id="user_group" style="display: none">Clinician</div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/template/footer.jsp" />
    </div>
</div>
</div>
</body>
</html>
