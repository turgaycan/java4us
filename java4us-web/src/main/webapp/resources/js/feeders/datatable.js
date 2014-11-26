//var feedMessages = angular.module('feedMessages', []);
//
//// Not Necessary to Create Service, Same can be done in Controller also as
//feedMessages.service('filteredListService', function () {
//
//    this.searched = function (valLists, toSearch) {
//        return _.filter(valLists,
//
//            function (i) {
//                /* Search Text in all 3 fields */
//                return searchUtil(i, toSearch);
//            });
//    };
//
//    this.paged = function (valLists, pageSize) {
//        retVal = [];
//        for (var i = 0; i < valLists.length; i++) {
//            if (i % pageSize === 0) {
//                retVal[Math.floor(i / pageSize)] = [valLists[i]];
//            } else {
//                retVal[Math.floor(i / pageSize)].push(valLists[i]);
//            }
//        }
//        return retVal;
//    };
//
//});
//
//// Inject Custom Service Created by us and Global service $filter. This is one
//// way of specifying dependency Injection
//var FeedMessageController = feedMessages.controller('FeedMessageController', function ($scope, $filter, filteredListService, $http) {
////var FeedMessageController = feedMessages.controller('FeedMessageController', function ($scope, $filter, $http) {
//
//    $scope.pageSize = 4;
//    $scope.getFeedMessagesDataFromServer = function () {
//        $http({method: 'GET', url: 'http://localhost:8099/feeders/feedmessages'}).
//            success(function (data, status, headers, config) {
//                $scope.allItems = data;
//                alert("test " + data);
//            }).
//            error(function (data, status, headers, config) {
//                // called asynchronously if an error occurs
//                // or server returns response with an error status.
//                alert("Data gelmedi....");
//            });
//
//    };
//    $scope.reverse = false;
//
//    $scope.resetAll = function () {
//        $scope.filteredList = $scope.allItems;
//        $scope.id = '';
//        $scope.title = '';
//        $scope.link = '';
//        $scope.pubdate = '';
//        $scope.createDate = '';
//        $scope.category = '';
//        $scope.status = '';
//        $scope.searchText = '';
//        $scope.currentPage = 0;
//        $scope.Header = ['', '', ''];
//    }
//
//    $scope.add = function () {
//        $scope.allItems.push({
//            id: $scope.id,
//            title: $scope.title,
//            link: $scope.link,
//            pubdate: $scope.pubdate,
//            createDate: $scope.createDate,
//            category: $scope.category,
//            status: $scope.status
//        });
//        $scope.resetAll();
//    }
//
//    $scope.search = function () {
//        $scope.filteredList = filteredListService.searched($scope.allItems, $scope.searchText);
//
//        if ($scope.searchText == '') {
//            $scope.filteredList = $scope.allItems;
//        }
//        $scope.pagination();
//    }
//
//    // Calculate Total Number of Pages based on Search Result
//    $scope.pagination = function () {
//        $scope.ItemsByPage = filteredListService.paged($scope.filteredList, $scope.pageSize);
//    };
//
//    $scope.setPage = function () {
//        $scope.currentPage = this.n;
//    };
//
//    $scope.firstPage = function () {
//        $scope.currentPage = 0;
//    };
//
//    $scope.lastPage = function () {
//        $scope.currentPage = $scope.ItemsByPage.length - 1;
//    };
//
//    $scope.range = function (input, total) {
//        var ret = [];
//        if (!total) {
//            total = input;
//            input = 0;
//        }
//        for (var i = input; i < total; i++) {
//            if (i != 0 && i != total - 1) {
//                ret.push(i);
//            }
//        }
//        return ret;
//    };
//
//    $scope.sort = function (sortBy) {
//        $scope.resetAll();
//
//        $scope.columnToOrder = sortBy;
//
//        // $Filter - Standard Service
//        $scope.filteredList = $filter('orderBy')($scope.filteredList, $scope.columnToOrder, $scope.reverse);
//
//        if ($scope.reverse) iconName = 'glyphicon glyphicon-chevron-up';
//        else iconName = 'glyphicon glyphicon-chevron-down';
//
//        if (sortBy === 'id') {
//            $scope.Header[0] = iconName;
//        } else if (sortBy === 'title') {
//            $scope.Header[1] = iconName;
//        } else {
//            $scope.Header[2] = iconName;
//        }
//
//        $scope.reverse = !$scope.reverse;
//
//        $scope.pagination();
//    };
//
//    // By Default sort ny Name
//    $scope.sort('title');
//
//});
//
//function searchUtil(item, toSearch) {
//    /* Search Text in all 3 fields */
//    return (item.link.toLowerCase().indexOf(toSearch.toLowerCase()) > -1 ||
//        item.title.toLowerCase().indexOf(toSearch.toLowerCase()) > -1 ||
//        item.id == toSearch) ? true : false;
//}