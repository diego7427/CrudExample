<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Customer </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.ctrl.customeromer.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.customer.name" id="uname" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="address">Address</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.customer.address" id="address" class="form-control input-sm" placeholder="Enter your Address." required />
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="status">Status</label>
	                        <div class="col-md-7">
	                        	<div ng-init="loadComboBox()">
 							   		<select class="form-control input-sm combo-status-width" ng-model="ctrl.customer.status"  placeholder="choose an option" ng-options="elem.description for elem in ctrl.getAllComboValues() track by elem.id"  required >
									</select>
								</div>
	                        </div>
	                    </div>
	                </div>
	                  <div ng-if="!ctrl.getEdition()">
			                <div class="row">
			                    <div class="form-group col-md-12">
			                        <label class="col-md-2 control-lable" for="note">Note</label>
			                        <div class="col-md-7">
			                            <input type="text" ng-model="ctrl.note" id="address" class="form-control input-sm" placeholder="Enter the first note." required />
			                        </div>
			                    </div>
			                </div>
		
			                <div class="row">
			                    <div class="form-actions floatRight">
			                        <input type="submit"  value="Add" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
			                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
			                    </div>
			                </div>
	                  </div >
	                  <div ng-if="ctrl.getEdition()">
					        <!-- Default panel contents -->
					        <div class="panel-heading"><span class="lead">List of Notes </span></div>
							<div class="panel-body">
								<div class="table-responsive">
							        <table class="table table-hover">
							            <thead>
							            <tr>
							                <th>ID</th>
							                <th>DESCRIPTION</th>
							                 <th width="100"></th>
							                <th width="100"></th>
							            </tr>
							            </thead>
							            <tbody>
							            <tr ng-repeat="u in ctrl.customer.notes">
							                <td>{{u.id}}</td>
							                 <td><input type="text" ng-model="u.description" class="form-control" /></td>
							                 <td></td>
							                <td><button type="button" ng-click="ctrl.removeNote($index)" class="btn btn-success custom-width">Delete</button></td>
							            </tr>
							            <tr>
								        	<td><button type="button" class="btn btn-primary" ng-click="ctrl.addNote()">Add note</button></td>
								          	<td></td>
								          	<td></td>
								          	<td></td>
								          	<td></td>
								        </tr>
							            </tbody>
							        </table>		
								</div>
							</div>
							 <div class="row">
			                    <div class="form-actions floatRight">
			                        <input type="submit"  value="Update" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
			                    </div>
			                </div>
					    </div>
	            </form>
    	    </div>
		</div>	
    </div>
    
    <div class="panel panel-default">
    
    <div ng-if="!ctrl.getEdition()">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Customers </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>NAME</th>
		                <th>STATUS</th>
		                <th>ADDRESS</th>
		                <th>#Notes</th>
		                 <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllCustomers()">
		                <td>{{u.id}}</td>
		                <td>{{u.name}}</td>
		                 <td>{{u.status.description}}</td>
		                <td>{{u.address}}</td>
		                <td>{{u.notes.length}}</td>
		                <td><button type="button" ng-click="ctrl.editCustomer(u.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeCustomer(u.id)" class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
    </div>

    
</div>