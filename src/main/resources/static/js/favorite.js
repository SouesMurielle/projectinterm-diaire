angular
	.module("favoriteApp", [])
	.directive('scrollOnClick', function() {
        return {
            restrict: 'A',
            link: function(scope, $elm, attrs) {
                var idToScroll = attrs.href;
                $elm.on('click', function() {
                    var $target;
                    if (idToScroll) {
                        $target = $(idToScroll);
                    } else {
                        $target = $elm;
                    }
                    $("body").animate({scrollTop: $target.offset().top});
                });
            }
        }
    })
	.controller("FavoritesController", function ($scope, $http) {
		$scope.categories = [];
		$scope.realCategories = [];
		$scope.favorites = [];

		$scope.categoryList = {
		    filter : 0
		};

		$scope.mode = "view";

		$scope.favorite = {};

		$scope.category = {};

		$scope.setMode = function (text) {
			if (text === "creationFavorite") {
				$scope.realCategories = $scope.categories.filter(function (c) {
					return c.id !== 0;
				});
				var idx = $scope.realCategories
					.map(function (c) {
						return c.id;
					})
					.indexOf($scope.categoryList.filter);
				if (idx < 0) idx = 0;

				$scope.favorite = {
					link: "",
					label: "",
					categoryList: $scope.realCategories[idx].id
				};
			} else if (text === "creationCategory") {
			    $scope.category = {
			        label: ""
			    };
			}

			$scope.mode = text;
		};

		$scope.cancel = function () {
			$scope.setMode("view");
		};

		$scope.refresh = function () {
//            console.log($http.get("api/category").value);
			$http.get("api/category").then(function (response) {
				$scope.categories = [{ id: 0, label: "All", references: 0 }];
				response.data.forEach((d) => {
//				    console.log(d.label)
					$scope.categories.push(d);
					$scope.categories[0].references += d.references;
				});

				$http.get("api/favorite").then(function (response) {
//					console.log(response);
					$scope.favorites = response.data.filter(
						(f) =>
							$scope.categoryList.filter === 0 ||
							f.category.id === $scope.categoryList.filter
					);
				});
			});
		};

		/* ----- FAVORITES ----- */

		$scope.setUpdateFavorite = function (f) {
		    $scope.realCategories = $scope.categories.filter(function(c) {
		    return c.id !== 0
		    });
		    var idx = $scope.realCategories
		    .map(function(c) {
		    return c.id
		    })
		    .indexOf(f.category.id);
//		    .indexOf($scope.categoryList.filter);
		    console.log("idx : " + idx);
            if (idx < 0) idx = 0;

            $scope.setMode('updateFavorite');

            $scope.favorite = {
                id : f.id,
                label : f.label,
                link : f.link,
//                category: f.category.id
                category: $scope.realCategories[idx].id
            };
		};

		$scope.createFavorite = function () {
			$http
				.post("api/" + $scope.favorite.category + "/favorite", {
					id: null,
					label: $scope.favorite.label,
					link: $scope.favorite.link,
				})
				.then(
					function () {
						$scope.refresh();
						$scope.setMode("view");
					},
					function (error) {
						alert(error.data.message);
					}
				);
		};

		$scope.updateFavorite = function() {
            $http
                .post("api/" + $scope.favorite.category + "/favorite", {
                    id: $scope.favorite.id,
                    label: $scope.favorite.label,
                    link: $scope.favorite.link
                })
                .then(
                    function () {
                        $scope.refresh();
                        $scope.setMode("view");
                    },
                    function (error) {
                        alert(error.data.message);
                    }
                );
		}

        $scope.deleteFavorite = function(id) {
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    $http.delete('api/favorite/' + id).then(
                        function() {
                            $scope.refresh();
                            Swal.fire(
                                'Deleted!',
                                'Your file has been deleted.',
                                'success'
                            )
                                  }, function(error) {
                                      alert(error.data.message);
                                      Swal.fire(
                                          'Not deleted!',
                                          'Your file hasn\'t been deleted.',
                                          'success'
                                      )
                                  }
                    )
              }
            })
        }

        /* ----- CATEGORIES ----- */

		$scope.setUpdateCategory = function (cat) {

            $scope.setMode('updateCategory');
            console.log("1/ " + cat);
//            console.log("1/ " + $scope.categoryList);

            $scope.category = {
                id : cat.id,
                label : cat.label
            };
		}

		$scope.createCategory = function () {
//            console.log("1/ " + $scope.categoryList);
			$http
				.post("api/category", {
					id: null,
					label: $scope.category.label
				})
				.then(
					function () {
						$scope.refresh();
						$scope.setMode("view");
					},
					function (error) {
						alert(error.data.message);
					}
				);
		};

		$scope.updateCategory = function() {
            $http
                .post("api/category", {
                    id: $scope.category.id,
                    label: $scope.category.label
                })
                .then(
                    function () {
                        $scope.refresh();
                        $scope.setMode("view");
                    },
                    function (error) {
                        alert(error.data.message);
                    }
                );
		}

        $scope.deleteCategory = function(id) {
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
//            console.log(result);
                if (result.isConfirmed) {
                    $http.delete('api/category/' + id).then(
                        function() {
                            $scope.refresh();
                            Swal.fire(
                                'Deleted!',
                                'Your file has been deleted.',
                                'success'
                            );
                        }, function(error) {
//                            alert(error.data.message);
                            Swal.fire(
                                'Impossible!',
                                'Your file hasn\'t been deleted.',
                                'fail'
                            );
                        }
                    );
                }
            })
        }

        $scope.format = function(item) {
            return (item.label + (item.id !== -1 ? ' (' + item.references + ')' : ''));
//            return (item.label + (item.id != 0 ? ' (' + item.references + ')' : ''));
        }

		$scope.refresh();
	});
