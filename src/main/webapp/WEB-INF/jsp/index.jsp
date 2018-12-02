<html>
	<head>
	<style>
        .grid-item {
            background-color: rgba(255, 255, 255, 0.8);
            border: 1px solid rgba(0, 0, 0, 0.8);
            padding: 10px;
            font-size: 13px;
            text-align: center;
        }

        .loader {
            border: 16px solid #f3f3f3;
            border-radius: 50%;
            border-top: 16px solid #3498db;
            width: 120px;
            height: 120px;
            margin-left:40%;
            -webkit-animation: spin 2s linear infinite;
            /* Safari */
            animation: spin 2s linear infinite;
        }

        /* Safari */
        @-webkit-keyframes spin {
            0% {
                -webkit-transform: rotate(0deg);
            }

            100% {
                -webkit-transform: rotate(360deg);
            }
        }

        @keyframes spin {
            0% {
                transform: rotate(0deg);
            }

            100% {
                transform: rotate(360deg);
            }
        }
    </style>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
		<script src="resources/js/app.js"></script>
	</head>
	<body ng-app="myApp">
		<nav class="navbar navbar-default">
  			<div class="container-fluid">
    			<div class="navbar-header">
      				<a class="navbar-brand" href="#">AJ Movies</a>
    			</div>
	    		<ul class="nav navbar-nav navbar-right">
				      <li ng-show="authenticated"><a href="#!logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
   				 </ul>
  			</div>
		</nav>
		<div class="" id ="loader"></div>
		<div ng-view>
			
		</div>
		
	</body>
</html>