'use strict';

angular.module('crudApp').controller('CustomerController',
    ['CustomerService', '$scope',  function( CustomerService, $scope) {

        var self = this;
        self.customer = {};
        self.customers=[];
        self.statusCombo={};

        self.submit = submit;
        self.getAllCustomers = getAllCustomers;
        self.createCustomer = createCustomer;
        self.updateCustomer = updateCustomer;
        self.removeCustomer = removeCustomer;
        self.editCustomer = editCustomer;
        self.reset = reset;
        self.getAllComboValues = getAllComboValues;
        self.getEdition = getEdition;
        self.addNote=addNote;
        self.removeNote=removeNote;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
        self.note = '';

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
        self.edit=false;
        
      

        function submit() {
            console.log('Submitting');
            if (self.customer.id === undefined || self.customer.id === null) {
                console.log('Saving New Customer', self.customer);
                createCustomer(self.customer);
            } else {
                updateCustomer(self.customer, self.customer.id);
                console.log('Customer updated with id ', self.customer.id);
            }
        }

        function createCustomer(customer) {
            console.log('About to create customer');
            customer.notes=[];
            var newNote={};
            newNote.description=self.note;
            customer.notes.push(newNote);
            CustomerService.createCustomer(customer)
                .then(
                    function (response) {
                        console.log('Customer created successfully');
                        self.successMessage = 'Customer created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.customer={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Customer');
                        self.errorMessage = 'Error while creating Customer: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
        
        


        function updateCustomer(customer, id){
            console.log('About to update customer');
            CustomerService.updateCustomer(customer, id)
                .then(
                    function (response){
                        console.log('Customer updated successfully');
                        self.successMessage='Customer updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        reset();
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Customer');
                        self.errorMessage='Error while updating Customer '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeCustomer(id){
            console.log('About to remove Customer with id '+id);
            CustomerService.removeCustomer(id)
                .then(
                    function(){
                        console.log('Customer '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing customer '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllCustomers(){
            return CustomerService.getAllCustomers();
        }
        

        function editCustomer(id) {
            self.successMessage='';
            self.errorMessage='';
            CustomerService.getCustomer(id).then(
                function (customer) {
                    self.customer = customer;
                    self.edit=true;
                },
                function (errResponse) {
                    console.error('Error while removing customer ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.customer={};
            self.note = '';
            self.edit=false;
            $scope.myForm.$setPristine(); //reset Form
        }
        
        function getAllComboValues() {
            return CustomerService.getAllComboValues();
        }
        function getEdition(){
            return self.edit;
        }
        
        function addNote(){
            self.customer.notes.push({
              id: '',
              description: '',
            });
          }
          function removeNote(index){
              self.customer.notes.splice(index, 1);
              $scope.myForm.$pristine=false;
          }
        
    }


    ]);