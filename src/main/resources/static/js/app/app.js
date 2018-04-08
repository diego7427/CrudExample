var app = angular.module('crudApp', [ 'ui.router', 'ngStorage' ]);

app.constant('urls', {
    BASE : 'http://localhost:8080/CustomerCRUDApp',
    USER_SERVICE_API : 'http://localhost:8080/CustomerCRUDApp/api/customer/'
});

app.config([
        '$stateProvider',
        '$urlRouterProvider',
        function($stateProvider, $urlRouterProvider) {

            $stateProvider.state('home', {
                url : '/',

                templateUrl : 'partials/list',
                controller : 'CustomerController',
                controllerAs : 'ctrl',
                resolve : {
                    customers : function($q, CustomerService) {
                        console.log('Load all customers');
                        var deferred = $q.defer();
                        CustomerService.loadAllCustomers().then(
                                deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    },
                    statusCombo : function($q, CustomerService) {
                        console.log('Load all customers');
                        var deferred = $q.defer();
                        CustomerService.loadAllComboValues().then(
                                deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }

            });
            $urlRouterProvider.otherwise('/');
        } ]);
