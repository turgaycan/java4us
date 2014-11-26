<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script>
    var feedMessages = angular.module('feedMessages', []);

    // Not Necessary to Create Service, Same can be done in Controller also as
    feedMessages.service('filteredListService', function () {

        this.searched = function (valLists, toSearch) {
            return _.filter(valLists,

                    function (i) {
                        /* Search Text in all 3 fields */
                        return searchUtil(i, toSearch);
                    });
        };

        this.paged = function (valLists, pageSize) {
            retVal = [];
            for (var i = 0; i < valLists.length; i++) {
                if (i % pageSize === 0) {
                    retVal[Math.floor(i / pageSize)] = [valLists[i]];
                } else {
                    retVal[Math.floor(i / pageSize)].push(valLists[i]);
                }
            }
            return retVal;
        };

    });

    // Inject Custom Service Created by us and Global service $filter. This is one
    // way of specifying dependency Injection
    var FeedMessageController = feedMessages.controller('FeedMessageController', function ($scope, $filter, filteredListService, $http) {
//var FeedMessageController = feedMessages.controller('FeedMessageController', function ($scope, $filter, $http) {

        $scope.pageSize = 4;
        $scope.getFeedMessagesDataFromServer =
                function ($scope, $http) {
                    $http({method: 'GET', url: '/feeders/feedmessages'})
                            .success(function (data, status, headers, config) {
                                $scope.allItems = data;
                            })
                            .error(function (data, status, headers, config) {
                                alert("failure");
                            });
                }
        $scope.reverse = false;

        $scope.resetAll = function () {
            $scope.filteredList = $scope.allItems;
            $scope.id = '';
            $scope.title = '';
            $scope.link = '';
            $scope.pubdate = '';
            $scope.createDate = '';
            $scope.category = '';
            $scope.status = '';
            $scope.searchText = '';
            $scope.currentPage = 0;
            $scope.Header = ['', '', ''];
        }

        $scope.add = function () {
            $scope.allItems.push({
                id: $scope.id,
                title: $scope.title,
                link: $scope.link,
                pubdate: $scope.pubdate,
                createDate: $scope.createDate,
                category: $scope.category,
                status: $scope.status
            });
            $scope.resetAll();
        }

        $scope.search = function () {
            $scope.filteredList = filteredListService.searched($scope.allItems, $scope.searchText);

            if ($scope.searchText == '') {
                $scope.filteredList = $scope.allItems;
            }
            $scope.pagination();
        }

        // Calculate Total Number of Pages based on Search Result
        $scope.pagination = function () {
            $scope.ItemsByPage = filteredListService.paged($scope.filteredList, $scope.pageSize);
        };

        $scope.setPage = function () {
            $scope.currentPage = this.n;
        };

        $scope.firstPage = function () {
            $scope.currentPage = 0;
        };

        $scope.lastPage = function () {
            $scope.currentPage = $scope.ItemsByPage.length - 1;
        };

        $scope.range = function (input, total) {
            var ret = [];
            if (!total) {
                total = input;
                input = 0;
            }
            for (var i = input; i < total; i++) {
                if (i != 0 && i != total - 1) {
                    ret.push(i);
                }
            }
            return ret;
        };

        $scope.sort = function (sortBy) {
            $scope.resetAll();

            $scope.columnToOrder = sortBy;

            // $Filter - Standard Service
            $scope.filteredList = $filter('orderBy')($scope.filteredList, $scope.columnToOrder, $scope.reverse);

            if ($scope.reverse) iconName = 'glyphicon glyphicon-chevron-up';
            else iconName = 'glyphicon glyphicon-chevron-down';

            if (sortBy === 'id') {
                $scope.Header[0] = iconName;
            } else if (sortBy === 'title') {
                $scope.Header[1] = iconName;
            } else {
                $scope.Header[2] = iconName;
            }

            $scope.reverse = !$scope.reverse;

            $scope.pagination();
        };

        // By Default sort ny Name
        $scope.sort('title');

    });

    function searchUtil(item, toSearch) {
        /* Search Text in all 3 fields */
        return (item.link.toLowerCase().indexOf(toSearch.toLowerCase()) > -1 ||
                item.title.toLowerCase().indexOf(toSearch.toLowerCase()) > -1 ||
                item.id == toSearch) ? true : false;
    }
    var feedMessageApp = angular.module("feedMessageApp", []);
    feedMessageApp.controller("FeedMessageController", [ '$scope', '$http',
        function ($scope, $http) {
            $http({method: 'GET', url: '/feeders/feedmessages'})
                    .success(function (data, status, headers, config) {
                        $scope.feedmessages = data;
                    })
                    .error(function (data, status, headers, config) {
                        alert("failure");
                    });
        } ])
</script>

<form id="edit-profile3" class="form-horizontal" ng-app="feedMessageApp">
    <fieldset ng-controller="FeedMessageController">

        <table class="table">
            <tr>
                <th>Title
                </th>
                <th>Link
                </th>
                <th>Category
                </th>
                <th>PubDate
                </th>
            </tr>
            <tr ng-repeat="feedmessage in feedmessages">
                <td>{{feedmessage.title}}
                </td>
                <td>{{feedmessage.link}}
                </td>
                <td>{{feedmessage.category}}
                </td>
                <td>{{feedmessage.pubDate}}
                </td>
            </tr>
        </table>

        <div ng-app="feedMessages">
            <div>
                <div ng-controller="Hello">
                    <div class="input-group">
                        <input class="form-control" ng-model="searchText" placeholder="Search" type="search"
                               ng-change="search()"/>
        <span class="input-group-addon">
        <span class="glyphicon glyphicon-search"></span>
        </span>
                    </div>
                    <table class="table  table-hover data-table myTable">
                        <thead>
                        <tr>
                            <th class="id"><a href="#" ng-click="sort('id',$event)">Id
                                <span class="{{Header[0]}}"></span>
                            </a>
                            </th>
                            <th class="title"><a ng-click="sort('title')" href="#"> Title
                                <span class="{{Header[1]}}"></span></a>
                            </th>
                            <th class="link"><a ng-click="sort('link')" href="#"> Link
                                <span class="{{Header[2]}}"></span></a>
                            </th>
                            <th class="pubdate"><a ng-click="sort('pubdate')" href="#"> Pubdate
                                <span class="{{Header[3]}}"></span></a>
                            </th>
                            <th class="createDate"><a ng-click="sort('createDate')" href="#"> Createdate
                                <span class="{{Header[4]}}"></span></a>
                            </th>
                            <th class="category"><a ng-click="sort('category')" href="#"> Category
                                <span class="{{Header[5]}}"></span></a>
                            </th>
                            <th class="status"><a ng-click="sort('status')" href="#"> Status
                                <span class="{{Header[6]}}"></span></a>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in feedmessages">
                            <td>{{item.id}}</td>
                            <td>{{item.title}}</td>
                            <td>{{item.link}}</td>
                            <td>{{item.pubdate}}</td>
                            <td>{{item.createDate}}</td>
                            <td>{{item.link}}</td>
                            <td>{{item.status}}</td>
                        </tr>
                        </tbody>
                    </table>
                    <ul class="pagination pagination-sm">
                        <li ng-class="{active:0}"><a href="#" ng-click="firstPage()">First</a>

                        </li>
                        <li ng-repeat="n in range(ItemsByPage.length)"><a href="#" ng-click="setPage()"
                                                                          ng-bind="n+1">1</a>

                        </li>
                        <li><a href="#" ng-click="lastPage()">Last</a>

                        </li>
                    </ul>
                </div>
                <!-- Ends Controller -->
            </div>
    </fieldset>
</form>