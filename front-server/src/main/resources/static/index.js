(function ($localStorage) {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'home/home.html',
                controller: 'homeController'
            })
            .when('/products', {
                templateUrl: 'products/products.html',
                controller: 'productsController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/order_confirmation', {
                templateUrl: 'order_confirmation/order_confirmation.html',
                controller: 'orderConfirmationController'
            })
            .when('/order_result/:orderId', {
                templateUrl: 'order_result/order_result.html',
                controller: 'orderResultController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    const contextPath = 'http://localhost:5555';

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = $localStorage.currentUser.token;
        }

        $http.post(contextPath + '/api/v1/cart')
            .then(function successCallback(response) {
                $localStorage.happyCartUuid = response.data;
            });
    }
})();

angular.module('app').controller('indexController', function ($scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:5555';

    $scope.tryToAuth = function () {

        $http.post(contextPath + '/api/v1/auth/login', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = response.data.token;
                    $localStorage.currentUser = {email: $scope.user.email, token: response.data.token};

                    $scope.currentUserName = $scope.user.email;

                    $scope.user.email = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });

        $http.post(contextPath + '/api/v1/cart')
            .then(function successCallback(response) {
                $localStorage.happyCartUuid = response.data;
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();

        $http.post(contextPath + '/api/v1/cart')
            .then(function successCallback(response) {
                $localStorage.happyCartUuid = response.data;
            });

        $location.path('/');
        if ($scope.user.email) {
            $scope.user.email = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
            return true;
        } else {
            return false;
        }
    };
});