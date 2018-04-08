'use strict';

angular.module('crudApp').factory('CustomerService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllCustomers: loadAllCustomers,
                getAllCustomers: getAllCustomers,
                getCustomer: getCustomer,
                createCustomer: createCustomer,
                updateCustomer: updateCustomer,
                removeCustomer: removeCustomer,
                loadAllComboValues: loadAllComboValues,
                getAllComboValues: getAllComboValues
            };

            return factory;

            function loadAllCustomers() {
                console.log('Fetching all customers');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all customers');
                            $localStorage.customers = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading customers');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function loadAllComboValues() {
                console.log('Fetching all customers');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API+'status')
                    .then(
                        function (response) {
                            console.log('Fetched successfully all customers');
                            $localStorage.statusCombo = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading customers');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            

            function getAllCustomers(){
                return $localStorage.customers;
            }
            function getAllComboValues(){
                return $localStorage.statusCombo;
            }


            function getCustomer(id) {
                console.log('Fetching Customer with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Customer with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading customer with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createCustomer(customer) {
                console.log('Creating Customer');
                var deferred = $q.defer();
                $http.post(urls.USER_SERVICE_API, customer)
                    .then(
                        function (response) {
                            loadAllCustomers();
                            self.edit=false;
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Customer : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateCustomer(customer, id) {
                console.log('Updating Customer with id '+id);
                var deferred = $q.defer();
                $http.put(urls.USER_SERVICE_API + id, customer)
                    .then(
                        function (response) {
                            loadAllCustomers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Customer with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeCustomer(id) {
                console.log('Removing Customer with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllCustomers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Customer with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);