angular.module('app').controller('orderResultController', function ($scope, $http, $location, $routeParams) {
    const contextPath = 'http://localhost:5555';

    $scope.showOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders/' + $routeParams.orderId,
            method: 'GET'
        }).then(function (response) {
            $scope.OrderInfo = response.data;
        });
    };

    $scope.showOrder();
});