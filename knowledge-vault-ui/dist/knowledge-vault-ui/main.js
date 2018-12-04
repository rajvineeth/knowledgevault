(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/_models/index.ts":
/*!**********************************!*\
  !*** ./src/app/_models/index.ts ***!
  \**********************************/
/*! exports provided: User */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _user__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./user */ "./src/app/_models/user.ts");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "User", function() { return _user__WEBPACK_IMPORTED_MODULE_0__["User"]; });




/***/ }),

/***/ "./src/app/_models/user.ts":
/*!*********************************!*\
  !*** ./src/app/_models/user.ts ***!
  \*********************************/
/*! exports provided: User */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "User", function() { return User; });
var User = /** @class */ (function () {
    function User() {
    }
    return User;
}());



/***/ }),

/***/ "./src/app/_services/alert.service.ts":
/*!********************************************!*\
  !*** ./src/app/_services/alert.service.ts ***!
  \********************************************/
/*! exports provided: AlertService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AlertService", function() { return AlertService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AlertService = /** @class */ (function () {
    function AlertService(router) {
        var _this = this;
        this.router = router;
        this.subject = new rxjs__WEBPACK_IMPORTED_MODULE_2__["Subject"]();
        this.keepAfterNavigationChange = false;
        // clear alert message on route change
        router.events.subscribe(function (event) {
            if (event instanceof _angular_router__WEBPACK_IMPORTED_MODULE_1__["NavigationStart"]) {
                if (_this.keepAfterNavigationChange) {
                    // only keep for a single location change
                    _this.keepAfterNavigationChange = false;
                }
                else {
                    // clear alert
                    _this.subject.next();
                }
            }
        });
    }
    AlertService.prototype.success = function (message, keepAfterNavigationChange) {
        if (keepAfterNavigationChange === void 0) { keepAfterNavigationChange = false; }
        this.keepAfterNavigationChange = keepAfterNavigationChange;
        this.subject.next({ type: 'success', text: message });
    };
    AlertService.prototype.error = function (message, keepAfterNavigationChange) {
        if (keepAfterNavigationChange === void 0) { keepAfterNavigationChange = false; }
        this.keepAfterNavigationChange = keepAfterNavigationChange;
        this.subject.next({ type: 'error', text: message });
    };
    AlertService.prototype.getMessage = function () {
        return this.subject.asObservable();
    };
    AlertService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]])
    ], AlertService);
    return AlertService;
}());



/***/ }),

/***/ "./src/app/_services/authentication.service.ts":
/*!*****************************************************!*\
  !*** ./src/app/_services/authentication.service.ts ***!
  \*****************************************************/
/*! exports provided: AuthenticationService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthenticationService", function() { return AuthenticationService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm5/operators/index.js");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../environments/environment */ "./src/environments/environment.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var AuthenticationService = /** @class */ (function () {
    function AuthenticationService(http) {
        this.http = http;
    }
    AuthenticationService.prototype.login = function (username, password) {
        return this.http.post(_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].apiUrl + "/user/login", { username: username, password: password })
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_2__["map"])(function (user) {
            // login successful if there's a jwt token in the response
            if (user && user.token) {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('currentUser', JSON.stringify(user));
            }
            return user;
        }));
    };
    AuthenticationService.prototype.logout = function () {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    };
    AuthenticationService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], AuthenticationService);
    return AuthenticationService;
}());



/***/ }),

/***/ "./src/app/_services/index.ts":
/*!************************************!*\
  !*** ./src/app/_services/index.ts ***!
  \************************************/
/*! exports provided: AlertService, AuthenticationService, UserService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _alert_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./alert.service */ "./src/app/_services/alert.service.ts");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "AlertService", function() { return _alert_service__WEBPACK_IMPORTED_MODULE_0__["AlertService"]; });

/* harmony import */ var _authentication_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./authentication.service */ "./src/app/_services/authentication.service.ts");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "AuthenticationService", function() { return _authentication_service__WEBPACK_IMPORTED_MODULE_1__["AuthenticationService"]; });

/* harmony import */ var _user_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./user.service */ "./src/app/_services/user.service.ts");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "UserService", function() { return _user_service__WEBPACK_IMPORTED_MODULE_2__["UserService"]; });






/***/ }),

/***/ "./src/app/_services/user.service.ts":
/*!*******************************************!*\
  !*** ./src/app/_services/user.service.ts ***!
  \*******************************************/
/*! exports provided: UserService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserService", function() { return UserService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../environments/environment */ "./src/environments/environment.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var UserService = /** @class */ (function () {
    function UserService(http) {
        this.http = http;
    }
    UserService.prototype.getAll = function () {
        return this.http.get(_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].apiUrl + "/users");
    };
    UserService.prototype.getById = function (id) {
        return this.http.get(_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].apiUrl + "/users/" + id);
    };
    UserService.prototype.register = function (user) {
        console.log('got http post');
        return this.http.post(_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].apiUrl2 + "/api/v1/saveuser", user);
    };
    UserService.prototype.update = function (user) {
        return this.http.put(_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].apiUrl + "/users/" + user.id, user);
    };
    UserService.prototype.delete = function (id) {
        return this.http.delete(_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].apiUrl + "/users/" + id);
    };
    UserService.prototype.send = function (user) {
        // console.log("got http post");
        return this.http.get(_environments_environment__WEBPACK_IMPORTED_MODULE_2__["environment"].apiUrl3 + "/kv/" + user);
    };
    UserService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], UserService);
    return UserService;
}());



/***/ }),

/***/ "./src/app/app-routing.module.ts":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony import */ var _register_register_component__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./register/register.component */ "./src/app/register/register.component.ts");
/* harmony import */ var _home_home_component__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./home/home.component */ "./src/app/home/home.component.ts");
/* harmony import */ var _login_login_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./login/login.component */ "./src/app/login/login.component.ts");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _query_results_query_results_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./query-results/query-results.component */ "./src/app/query-results/query-results.component.ts");
/* harmony import */ var _profile_sme_sme_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./profile/sme/sme.component */ "./src/app/profile/sme/sme.component.ts");
/* harmony import */ var _card_detail_card_detail_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./card-detail/card-detail.component */ "./src/app/card-detail/card-detail.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};








var routes = [
    { path: 'carddetail/:medicalCondition', component: _card_detail_card_detail_component__WEBPACK_IMPORTED_MODULE_7__["CardDetailComponent"] },
    { path: 'login', component: _login_login_component__WEBPACK_IMPORTED_MODULE_2__["LoginComponent"] },
    { path: 'sme', component: _profile_sme_sme_component__WEBPACK_IMPORTED_MODULE_6__["SmeComponent"] },
    { path: 'register', component: _register_register_component__WEBPACK_IMPORTED_MODULE_0__["RegisterComponent"] },
    { path: 'home', component: _home_home_component__WEBPACK_IMPORTED_MODULE_1__["HomeComponent"] },
    { path: 'user', redirectTo: '/home', pathMatch: 'full' },
    { path: 'queryresults', component: _query_results_query_results_component__WEBPACK_IMPORTED_MODULE_5__["QueryResultsComponent"] },
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: '**', redirectTo: '/home', pathMatch: 'full' }
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_3__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forRoot(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"]]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());



/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "app-footer {\n  position: absolute;\n  /* flex: 1 1 auto; */\n  bottom: 0;\n  left: 0;\n  width: 100%;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvYXBwLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDRSxtQkFBbUI7RUFDbkIscUJBQXFCO0VBQ3JCLFVBQVU7RUFDVixRQUFRO0VBQ1IsWUFBWTtDQUNiIiwiZmlsZSI6InNyYy9hcHAvYXBwLmNvbXBvbmVudC5jc3MiLCJzb3VyY2VzQ29udGVudCI6WyJhcHAtZm9vdGVyIHtcbiAgcG9zaXRpb246IGFic29sdXRlO1xuICAvKiBmbGV4OiAxIDEgYXV0bzsgKi9cbiAgYm90dG9tOiAwO1xuICBsZWZ0OiAwO1xuICB3aWR0aDogMTAwJTtcbn0iXX0= */"

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-header></app-header>\n<app-footer></app-footer>\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'knowledge-vault-ui';
    }
    AppComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _login_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./login.service */ "./src/app/login.service.ts");
/* harmony import */ var _data_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./data.service */ "./src/app/data.service.ts");
/* harmony import */ var _core_material_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./core/material.module */ "./src/app/core/material.module.ts");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm5/animations.js");
/* harmony import */ var _lib___WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../lib/ */ "./src/lib/index.ts");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _login_login_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./login/login.component */ "./src/app/login/login.component.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _home_home_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./home/home.component */ "./src/app/home/home.component.ts");
/* harmony import */ var _register_register_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./register/register.component */ "./src/app/register/register.component.ts");
/* harmony import */ var _header_header_component__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! ./header/header.component */ "./src/app/header/header.component.ts");
/* harmony import */ var _footer_footer_component__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! ./footer/footer.component */ "./src/app/footer/footer.component.ts");
/* harmony import */ var _user_query_user_query_component__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! ./user-query/user-query.component */ "./src/app/user-query/user-query.component.ts");
/* harmony import */ var _query_results_query_results_component__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! ./query-results/query-results.component */ "./src/app/query-results/query-results.component.ts");
/* harmony import */ var _profile_profile_module__WEBPACK_IMPORTED_MODULE_17__ = __webpack_require__(/*! ./profile/profile.module */ "./src/app/profile/profile.module.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_18__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _card_detail_card_detail_component__WEBPACK_IMPORTED_MODULE_19__ = __webpack_require__(/*! ./card-detail/card-detail.component */ "./src/app/card-detail/card-detail.component.ts");
/* harmony import */ var _share_service__WEBPACK_IMPORTED_MODULE_20__ = __webpack_require__(/*! ./share.service */ "./src/app/share.service.ts");
/* harmony import */ var _angular_flex_layout__WEBPACK_IMPORTED_MODULE_21__ = __webpack_require__(/*! @angular/flex-layout */ "./node_modules/@angular/flex-layout/esm5/flex-layout.es5.js");
/* harmony import */ var _card_card_component__WEBPACK_IMPORTED_MODULE_22__ = __webpack_require__(/*! ./card/card.component */ "./src/app/card/card.component.ts");
/* harmony import */ var _card_symptoms_symptoms_component__WEBPACK_IMPORTED_MODULE_23__ = __webpack_require__(/*! ./card/symptoms/symptoms.component */ "./src/app/card/symptoms/symptoms.component.ts");
/* harmony import */ var _card_expansion_panels_expansion_panels_component__WEBPACK_IMPORTED_MODULE_24__ = __webpack_require__(/*! ./card/expansion-panels/expansion-panels.component */ "./src/app/card/expansion-panels/expansion-panels.component.ts");
/* harmony import */ var _user_query_service__WEBPACK_IMPORTED_MODULE_25__ = __webpack_require__(/*! ./user-query.service */ "./src/app/user-query.service.ts");
/* harmony import */ var _registration_service__WEBPACK_IMPORTED_MODULE_26__ = __webpack_require__(/*! ./registration.service */ "./src/app/registration.service.ts");
/* harmony import */ var _card_accordion_accordion_component__WEBPACK_IMPORTED_MODULE_27__ = __webpack_require__(/*! ./card/accordion/accordion.component */ "./src/app/card/accordion/accordion.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};




























var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_4__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_8__["AppComponent"],
                _login_login_component__WEBPACK_IMPORTED_MODULE_9__["LoginComponent"],
                _home_home_component__WEBPACK_IMPORTED_MODULE_11__["HomeComponent"],
                _register_register_component__WEBPACK_IMPORTED_MODULE_12__["RegisterComponent"],
                _header_header_component__WEBPACK_IMPORTED_MODULE_13__["HeaderComponent"],
                _footer_footer_component__WEBPACK_IMPORTED_MODULE_14__["FooterComponent"],
                _user_query_user_query_component__WEBPACK_IMPORTED_MODULE_15__["UserQueryComponent"],
                _query_results_query_results_component__WEBPACK_IMPORTED_MODULE_16__["QueryResultsComponent"],
                _card_detail_card_detail_component__WEBPACK_IMPORTED_MODULE_19__["CardDetailComponent"],
                _card_card_component__WEBPACK_IMPORTED_MODULE_22__["CardComponent"],
                _card_symptoms_symptoms_component__WEBPACK_IMPORTED_MODULE_23__["SymptomsComponent"],
                _card_expansion_panels_expansion_panels_component__WEBPACK_IMPORTED_MODULE_24__["ExpansionPanelsComponent"],
                _card_accordion_accordion_component__WEBPACK_IMPORTED_MODULE_27__["AccordionComponent"],
            ],
            imports: [
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__["BrowserModule"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_7__["AppRoutingModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_10__["ReactiveFormsModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_10__["FormsModule"],
                _core_material_module__WEBPACK_IMPORTED_MODULE_2__["CustomMaterialModule"],
                _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_5__["BrowserAnimationsModule"],
                _lib___WEBPACK_IMPORTED_MODULE_6__["SpeechModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_18__["HttpClientModule"],
                _profile_profile_module__WEBPACK_IMPORTED_MODULE_17__["ProfileModule"],
                _angular_flex_layout__WEBPACK_IMPORTED_MODULE_21__["FlexLayoutModule"]
            ],
            providers: [
                { provide: 'SPEECH_LANG', useValue: 'en-US' },
                _angular_forms__WEBPACK_IMPORTED_MODULE_10__["FormsModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_10__["ReactiveFormsModule"],
                _share_service__WEBPACK_IMPORTED_MODULE_20__["ShareService"],
                _user_query_service__WEBPACK_IMPORTED_MODULE_25__["UserQueryService"],
                _data_service__WEBPACK_IMPORTED_MODULE_1__["DataService"],
                _registration_service__WEBPACK_IMPORTED_MODULE_26__["RegistrationService"],
                _login_service__WEBPACK_IMPORTED_MODULE_0__["LoginService"]
            ],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_8__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/card-detail/card-detail.component.css":
/*!*******************************************************!*\
  !*** ./src/app/card-detail/card-detail.component.css ***!
  \*******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NhcmQtZGV0YWlsL2NhcmQtZGV0YWlsLmNvbXBvbmVudC5jc3MifQ== */"

/***/ }),

/***/ "./src/app/card-detail/card-detail.component.html":
/*!********************************************************!*\
  !*** ./src/app/card-detail/card-detail.component.html ***!
  \********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div *ngIf=\"card\" >\n  <h2>{{card.MedicalCondition}}</h2>\n</div>\n\n<!-- \n<h2>Disease name</h2>\n<p>Description of 2 to 3 lines</p>\n<h3>Symptoms of the disease</h3>\n<ul>\n  <li>Symptom 1</li>\n  <li>Symptom 2</li>\n  <li>Symptom 3</li>\n</ul>\n<br>\n<h3>Causes</h3>\n<ul>\n  <li>Cause1</li>\n  <li>Cause2</li>\n  <li>Cause3</li>\n</ul>\n<h3>Click here to view documentation</h3> -->"

/***/ }),

/***/ "./src/app/card-detail/card-detail.component.ts":
/*!******************************************************!*\
  !*** ./src/app/card-detail/card-detail.component.ts ***!
  \******************************************************/
/*! exports provided: CardDetailComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CardDetailComponent", function() { return CardDetailComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _user_query_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../user-query.service */ "./src/app/user-query.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var CardDetailComponent = /** @class */ (function () {
    function CardDetailComponent(route, userQuery) {
        this.route = route;
        this.userQuery = userQuery;
    }
    CardDetailComponent.prototype.ngOnInit = function () {
        this.getCard();
    };
    CardDetailComponent.prototype.getCard = function () {
        var _this = this;
        var medicalConditionId = +this.route.snapshot.paramMap.get('medicalCondition');
        this.userQuery.getDisease(medicalConditionId)
            .subscribe(function (data) { return _this.card = data; });
    };
    CardDetailComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-card-detail',
            template: __webpack_require__(/*! ./card-detail.component.html */ "./src/app/card-detail/card-detail.component.html"),
            styles: [__webpack_require__(/*! ./card-detail.component.css */ "./src/app/card-detail/card-detail.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["ActivatedRoute"], _user_query_service__WEBPACK_IMPORTED_MODULE_2__["UserQueryService"]])
    ], CardDetailComponent);
    return CardDetailComponent;
}());



/***/ }),

/***/ "./src/app/card/accordion/accordion.component.css":
/*!********************************************************!*\
  !*** ./src/app/card/accordion/accordion.component.css ***!
  \********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2NhcmQvYWNjb3JkaW9uL2FjY29yZGlvbi5jb21wb25lbnQuY3NzIn0= */"

/***/ }),

/***/ "./src/app/card/accordion/accordion.component.html":
/*!*********************************************************!*\
  !*** ./src/app/card/accordion/accordion.component.html ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div *ngFor=\"let paragraph of paragraphs\" >\n  <app-expansion-panels [paragraph]=\"paragraph\" ></app-expansion-panels>\n</div>"

/***/ }),

/***/ "./src/app/card/accordion/accordion.component.ts":
/*!*******************************************************!*\
  !*** ./src/app/card/accordion/accordion.component.ts ***!
  \*******************************************************/
/*! exports provided: AccordionComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AccordionComponent", function() { return AccordionComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var AccordionComponent = /** @class */ (function () {
    function AccordionComponent() {
    }
    AccordionComponent.prototype.ngOnInit = function () {
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Array)
    ], AccordionComponent.prototype, "paragraphs", void 0);
    AccordionComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-accordion',
            template: __webpack_require__(/*! ./accordion.component.html */ "./src/app/card/accordion/accordion.component.html"),
            styles: [__webpack_require__(/*! ./accordion.component.css */ "./src/app/card/accordion/accordion.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], AccordionComponent);
    return AccordionComponent;
}());



/***/ }),

/***/ "./src/app/card/card.component.css":
/*!*****************************************!*\
  !*** ./src/app/card/card.component.css ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".card {\n    max-width: 400px;\n  }\n  \n  .header-image {\n    background-image: url('https://material.angular.io/assets/img/examples/shiba1.jpg');\n    background-size: cover;\n  }\n  \n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvY2FyZC9jYXJkLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7SUFDSSxpQkFBaUI7R0FDbEI7O0VBRUQ7SUFDRSxvRkFBb0Y7SUFDcEYsdUJBQXVCO0dBQ3hCIiwiZmlsZSI6InNyYy9hcHAvY2FyZC9jYXJkLmNvbXBvbmVudC5jc3MiLCJzb3VyY2VzQ29udGVudCI6WyIuY2FyZCB7XG4gICAgbWF4LXdpZHRoOiA0MDBweDtcbiAgfVxuICBcbiAgLmhlYWRlci1pbWFnZSB7XG4gICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKCdodHRwczovL21hdGVyaWFsLmFuZ3VsYXIuaW8vYXNzZXRzL2ltZy9leGFtcGxlcy9zaGliYTEuanBnJyk7XG4gICAgYmFja2dyb3VuZC1zaXplOiBjb3ZlcjtcbiAgfVxuICAiXX0= */"

/***/ }),

/***/ "./src/app/card/card.component.html":
/*!******************************************!*\
  !*** ./src/app/card/card.component.html ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-card class=\"card\">\n  <mat-card-header>\n    <div mat-card-avatar class=\"header-image\"></div>\n    <mat-card-title>{{card.MedicalCondition}}</mat-card-title>\n    <mat-card-subtitle>Disease Scientific name</mat-card-subtitle>\n  </mat-card-header>\n  <mat-card-content>\n    <mat-expansion-panel [expanded]=\"step === 0\" (opened)=\"setStep(0)\" hideToggle>\n      <mat-expansion-panel-header>\n        <mat-panel-title >\n          Symptoms\n        </mat-panel-title>\n        <!-- <mat-panel-description>\n          Tap to view symptoms\n        </mat-panel-description> -->\n      </mat-expansion-panel-header>\n      <app-symptoms [symptoms]=\"card.MedicalSymptoms\" ></app-symptoms>\n    </mat-expansion-panel>\n    <app-accordion [paragraphs]=\"card.Paragraphs\" ></app-accordion>\n  </mat-card-content>\n  <mat-card-actions>\n    <button mat-button routerLink=\"carddetail/{{card.medicalCondition}}\" >Detailed View</button>\n    <button mat-button>Save Card</button>\n  </mat-card-actions>\n</mat-card>"

/***/ }),

/***/ "./src/app/card/card.component.ts":
/*!****************************************!*\
  !*** ./src/app/card/card.component.ts ***!
  \****************************************/
/*! exports provided: CardComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CardComponent", function() { return CardComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _models_receivedQuery__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../models/receivedQuery */ "./src/app/models/receivedQuery.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var CardComponent = /** @class */ (function () {
    function CardComponent(router) {
        this.router = router;
    }
    CardComponent.prototype.ngOnInit = function () {
    };
    CardComponent.prototype.detailedView = function (card) {
        this.selectedCard = card;
        this.router.navigate(['carddetail/{{card.MedicalCondition}}']);
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", _models_receivedQuery__WEBPACK_IMPORTED_MODULE_2__["ReceivedQuery"])
    ], CardComponent.prototype, "card", void 0);
    CardComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-card',
            template: __webpack_require__(/*! ./card.component.html */ "./src/app/card/card.component.html"),
            styles: [__webpack_require__(/*! ./card.component.css */ "./src/app/card/card.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]])
    ], CardComponent);
    return CardComponent;
}());



/***/ }),

/***/ "./src/app/card/expansion-panels/expansion-panels.component.css":
/*!**********************************************************************!*\
  !*** ./src/app/card/expansion-panels/expansion-panels.component.css ***!
  \**********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".paragraph .mat-expansion-panel-header-title,\n.paragraph .mat-expansion-panel-header-description {\n  flex-basis: 0;\n}\n\n.paragraph .mat-expansion-panel-header-description {\n  justify-content: space-between;\n  align-items: center;\n}\n\n#dots {\n  color:#999;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvY2FyZC9leHBhbnNpb24tcGFuZWxzL2V4cGFuc2lvbi1wYW5lbHMuY29tcG9uZW50LmNzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTs7RUFFRSxjQUFjO0NBQ2Y7O0FBRUQ7RUFDRSwrQkFBK0I7RUFDL0Isb0JBQW9CO0NBQ3JCOztBQUNEO0VBQ0UsV0FBVztDQUNaIiwiZmlsZSI6InNyYy9hcHAvY2FyZC9leHBhbnNpb24tcGFuZWxzL2V4cGFuc2lvbi1wYW5lbHMuY29tcG9uZW50LmNzcyIsInNvdXJjZXNDb250ZW50IjpbIi5wYXJhZ3JhcGggLm1hdC1leHBhbnNpb24tcGFuZWwtaGVhZGVyLXRpdGxlLFxuLnBhcmFncmFwaCAubWF0LWV4cGFuc2lvbi1wYW5lbC1oZWFkZXItZGVzY3JpcHRpb24ge1xuICBmbGV4LWJhc2lzOiAwO1xufVxuXG4ucGFyYWdyYXBoIC5tYXQtZXhwYW5zaW9uLXBhbmVsLWhlYWRlci1kZXNjcmlwdGlvbiB7XG4gIGp1c3RpZnktY29udGVudDogc3BhY2UtYmV0d2VlbjtcbiAgYWxpZ24taXRlbXM6IGNlbnRlcjtcbn1cbiNkb3RzIHtcbiAgY29sb3I6Izk5OTtcbn0iXX0= */"

/***/ }),

/***/ "./src/app/card/expansion-panels/expansion-panels.component.html":
/*!***********************************************************************!*\
  !*** ./src/app/card/expansion-panels/expansion-panels.component.html ***!
  \***********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-card>\n  <mat-card-content>\n    <p>\n      {{ visiblePara\n      }}<span\n        *ngIf=\"hiddenPara\"\n        id=\"dots\"\n        (click)=\"showMore()\"\n        [ngStyle]=\"{ display: valueDots }\"\n        >...(show more)</span\n      ><span\n        id=\"more\"\n        (click)=\"showMore()\"\n        [ngStyle]=\"{ display: valuePara }\"\n        >{{ hiddenPara }}</span\n      >\n    </p>\n  </mat-card-content>\n  <mat-card-actions>\n      <button mat-button>View document</button>\n    </mat-card-actions>\n</mat-card>\n"

/***/ }),

/***/ "./src/app/card/expansion-panels/expansion-panels.component.ts":
/*!*********************************************************************!*\
  !*** ./src/app/card/expansion-panels/expansion-panels.component.ts ***!
  \*********************************************************************/
/*! exports provided: ExpansionPanelsComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ExpansionPanelsComponent", function() { return ExpansionPanelsComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var src_app_models_paragraph__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! src/app/models/paragraph */ "./src/app/models/paragraph.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var ExpansionPanelsComponent = /** @class */ (function () {
    function ExpansionPanelsComponent() {
        this.valuePara = 'none';
        this.valueDots = 'inline';
    }
    ExpansionPanelsComponent.prototype.ngOnInit = function () {
        if (this.paragraph.Content.length > 150) {
            this.visiblePara = this.paragraph.Content.substr(0, 150);
            this.hiddenPara = this.paragraph.Content.substr(150);
        }
        else {
            this.visiblePara = this.paragraph.Content;
            this.hiddenPara = null;
        }
    };
    ExpansionPanelsComponent.prototype.showMore = function () {
        if (this.valuePara === 'none') {
            this.valuePara = 'inline';
            this.valueDots = 'none';
        }
        else {
            this.valuePara = 'none';
            this.valueDots = 'inline';
        }
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", src_app_models_paragraph__WEBPACK_IMPORTED_MODULE_1__["Paragraph"])
    ], ExpansionPanelsComponent.prototype, "paragraph", void 0);
    ExpansionPanelsComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-expansion-panels',
            template: __webpack_require__(/*! ./expansion-panels.component.html */ "./src/app/card/expansion-panels/expansion-panels.component.html"),
            styles: [__webpack_require__(/*! ./expansion-panels.component.css */ "./src/app/card/expansion-panels/expansion-panels.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], ExpansionPanelsComponent);
    return ExpansionPanelsComponent;
}());



/***/ }),

/***/ "./src/app/card/symptoms/symptoms.component.css":
/*!******************************************************!*\
  !*** ./src/app/card/symptoms/symptoms.component.css ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".mat-list-icon {\n    color: rgba(0, 0, 0, 0.54);\n  }\n  \n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvY2FyZC9zeW1wdG9tcy9zeW1wdG9tcy5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0lBQ0ksMkJBQTJCO0dBQzVCIiwiZmlsZSI6InNyYy9hcHAvY2FyZC9zeW1wdG9tcy9zeW1wdG9tcy5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLm1hdC1saXN0LWljb24ge1xuICAgIGNvbG9yOiByZ2JhKDAsIDAsIDAsIDAuNTQpO1xuICB9XG4gICJdfQ== */"

/***/ }),

/***/ "./src/app/card/symptoms/symptoms.component.html":
/*!*******************************************************!*\
  !*** ./src/app/card/symptoms/symptoms.component.html ***!
  \*******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!-- <mat-list>\n  <mat-list-item>cough</mat-list-item>\n  <mat-list-item>cold</mat-list-item>\n  <mat-list-item>pain</mat-list-item>\n</mat-list> -->\n\n<div *ngFor=\"let symptom of symptoms\" >\n  <div>{{symptom}}</div>\n</div>\n\n<!-- \n<mat-list *ngFor = \"let symptom of symptoms\" dense>\n  <mat-list-item>  symptom  </mat-list-item>\n</mat-list> -->\n"

/***/ }),

/***/ "./src/app/card/symptoms/symptoms.component.ts":
/*!*****************************************************!*\
  !*** ./src/app/card/symptoms/symptoms.component.ts ***!
  \*****************************************************/
/*! exports provided: SymptomsComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SymptomsComponent", function() { return SymptomsComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var SymptomsComponent = /** @class */ (function () {
    function SymptomsComponent() {
    }
    SymptomsComponent.prototype.ngOnInit = function () {
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Array)
    ], SymptomsComponent.prototype, "symptoms", void 0);
    SymptomsComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-symptoms',
            template: __webpack_require__(/*! ./symptoms.component.html */ "./src/app/card/symptoms/symptoms.component.html"),
            styles: [__webpack_require__(/*! ./symptoms.component.css */ "./src/app/card/symptoms/symptoms.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], SymptomsComponent);
    return SymptomsComponent;
}());



/***/ }),

/***/ "./src/app/core/material.module.ts":
/*!*****************************************!*\
  !*** ./src/app/core/material.module.ts ***!
  \*****************************************/
/*! exports provided: CustomMaterialModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CustomMaterialModule", function() { return CustomMaterialModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var CustomMaterialModule = /** @class */ (function () {
    function CustomMaterialModule() {
    }
    CustomMaterialModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatToolbarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatDialogModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatTableModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatMenuModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatIconModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatExpansionModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatProgressSpinnerModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatListModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSidenavModule"]
            ],
            exports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatToolbarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatDialogModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatTableModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatMenuModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatIconModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatListModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSidenavModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatExpansionModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatProgressSpinnerModule"]
            ],
        })
    ], CustomMaterialModule);
    return CustomMaterialModule;
}());



/***/ }),

/***/ "./src/app/data.service.ts":
/*!*********************************!*\
  !*** ./src/app/data.service.ts ***!
  \*********************************/
/*! exports provided: DataService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DataService", function() { return DataService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm5/operators/index.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var httpOptions = {
    headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({
        'Content-Type': 'application/json',
    })
};
var DataService = /** @class */ (function () {
    function DataService(http) {
        this.http = http;
        // change to IP address of query-engine
        this._url = 'http://localhost:8185/api/v1/results';
    }
    DataService.prototype.getQuery = function () {
        console.log(this.http.get('recieved data: ' + this._url));
        return this.http.get(this._url).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_2__["catchError"])(function (err) {
            console.log(err.error);
            return Object(rxjs__WEBPACK_IMPORTED_MODULE_3__["throwError"])(err.error);
        }));
    };
    DataService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], DataService);
    return DataService;
}());



/***/ }),

/***/ "./src/app/footer/footer.component.css":
/*!*********************************************!*\
  !*** ./src/app/footer/footer.component.css ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "mat-toolbar {\n  background-color: #f3f3f3;\n  color: #bbb;\n  font-size: 1rem;\n}\n\na {\n  color: #5cb85c;\n  font-weight: bold;\n  text-decoration: none;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvZm9vdGVyL2Zvb3Rlci5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UsMEJBQTBCO0VBQzFCLFlBQVk7RUFDWixnQkFBZ0I7Q0FDakI7O0FBRUQ7RUFDRSxlQUFlO0VBQ2Ysa0JBQWtCO0VBQ2xCLHNCQUFzQjtDQUN2QiIsImZpbGUiOiJzcmMvYXBwL2Zvb3Rlci9mb290ZXIuY29tcG9uZW50LmNzcyIsInNvdXJjZXNDb250ZW50IjpbIm1hdC10b29sYmFyIHtcbiAgYmFja2dyb3VuZC1jb2xvcjogI2YzZjNmMztcbiAgY29sb3I6ICNiYmI7XG4gIGZvbnQtc2l6ZTogMXJlbTtcbn1cblxuYSB7XG4gIGNvbG9yOiAjNWNiODVjO1xuICBmb250LXdlaWdodDogYm9sZDtcbiAgdGV4dC1kZWNvcmF0aW9uOiBub25lO1xufSJdfQ== */"

/***/ }),

/***/ "./src/app/footer/footer.component.html":
/*!**********************************************!*\
  !*** ./src/app/footer/footer.component.html ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-toolbar role=\"footer\" class=\"footer\">\n  <mat-toolbar-row class=\"foot\" fxLayout=\"row wrap\" fxLayoutAlign=\"space between\">\n    <div style=\"margin:auto\">\n      <!-- <span class=\"foot-spacer\"></span> -->\n      <div fxShow=\"true\" fxHide.lt-md=\"true\" >\n        <span><a routerLink=\"/\">Knowledge Vault</a></span>\n        <span class=\"attribution\">\n          &copy; 2018. An interactive learning project from <a href=\"http://stackroute.in\">Stackroute</a>.\n        </span>\n      </div>\n    </div>\n  </mat-toolbar-row>\n</mat-toolbar>"

/***/ }),

/***/ "./src/app/footer/footer.component.ts":
/*!********************************************!*\
  !*** ./src/app/footer/footer.component.ts ***!
  \********************************************/
/*! exports provided: FooterComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FooterComponent", function() { return FooterComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var FooterComponent = /** @class */ (function () {
    function FooterComponent() {
    }
    FooterComponent.prototype.ngOnInit = function () {
    };
    FooterComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-footer',
            template: __webpack_require__(/*! ./footer.component.html */ "./src/app/footer/footer.component.html"),
            styles: [__webpack_require__(/*! ./footer.component.css */ "./src/app/footer/footer.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], FooterComponent);
    return FooterComponent;
}());



/***/ }),

/***/ "./src/app/header/header.component.css":
/*!*********************************************!*\
  !*** ./src/app/header/header.component.css ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".nav-fill-remaining-space {\n  flex: 1 1 auto;\n}\n\nmat-toolbar {\n  background-color:rgba(255, 255, 255, 0.3);\n  color: #bbb;\n}\n\n.example-container {\n  flex: 1 1 auto;\n  background-color: white;\n}\n\na {\n  color: #5cb85c;\n  font-weight: bold;\n  font-size: 4vw 4vh;\n  text-decoration: none;\n}\n\nbutton:hover {\n  color: black;\n}\n\nspan {\n  margin-right: 3%;\n}\n\n#logo {\n  -o-object-fit: cover;\n     object-fit: cover;\n  background-image: url('logo.jpg');\n  background-size: cover;\n  border: black;\n}\n\n.avatar {\n  border: black;\n  -o-object-fit: fill;\n     object-fit: fill;\n  background-image: url('user.png');\n  background-size: cover;\n}\n\n.nav-spacer {\n  flex: 1 1 auto;\n}\n\n.mat-sidenav-container{\n  min-height: 80vh !important;\n}\n\nmat-sidenav {\n  width: 30vw;\n  /* background: rgba(0, 255, 106, 0.6); */\n  /* background:rgba(255, 255, 255, 0.3); */\n  background: white;\n  background-size: cover;\n}\n\nbutton:visited {\n  color: black;\n}\n\nbutton:focus {\n  color: black;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvaGVhZGVyL2hlYWRlci5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UsZUFBZTtDQUNoQjs7QUFFRDtFQUNFLDBDQUEwQztFQUMxQyxZQUFZO0NBQ2I7O0FBRUQ7RUFDRSxlQUFlO0VBQ2Ysd0JBQXdCO0NBQ3pCOztBQUVEO0VBQ0UsZUFBZTtFQUNmLGtCQUFrQjtFQUNsQixtQkFBbUI7RUFDbkIsc0JBQXNCO0NBQ3ZCOztBQUVEO0VBQ0UsYUFBYTtDQUNkOztBQUVEO0VBQ0UsaUJBQWlCO0NBQ2xCOztBQUVEO0VBQ0UscUJBQWtCO0tBQWxCLGtCQUFrQjtFQUNsQixrQ0FBK0M7RUFDL0MsdUJBQXVCO0VBQ3ZCLGNBQWM7Q0FDZjs7QUFFRDtFQUNFLGNBQWM7RUFDZCxvQkFBaUI7S0FBakIsaUJBQWlCO0VBQ2pCLGtDQUErQztFQUMvQyx1QkFBdUI7Q0FDeEI7O0FBRUQ7RUFDRSxlQUFlO0NBQ2hCOztBQUVEO0VBQ0UsNEJBQTRCO0NBQzdCOztBQUVEO0VBQ0UsWUFBWTtFQUNaLHlDQUF5QztFQUN6QywwQ0FBMEM7RUFDMUMsa0JBQWtCO0VBQ2xCLHVCQUF1QjtDQUN4Qjs7QUFFRDtFQUNFLGFBQWE7Q0FDZDs7QUFFRDtFQUNFLGFBQWE7Q0FDZCIsImZpbGUiOiJzcmMvYXBwL2hlYWRlci9oZWFkZXIuY29tcG9uZW50LmNzcyIsInNvdXJjZXNDb250ZW50IjpbIi5uYXYtZmlsbC1yZW1haW5pbmctc3BhY2Uge1xuICBmbGV4OiAxIDEgYXV0bztcbn1cblxubWF0LXRvb2xiYXIge1xuICBiYWNrZ3JvdW5kLWNvbG9yOnJnYmEoMjU1LCAyNTUsIDI1NSwgMC4zKTtcbiAgY29sb3I6ICNiYmI7XG59XG5cbi5leGFtcGxlLWNvbnRhaW5lciB7XG4gIGZsZXg6IDEgMSBhdXRvO1xuICBiYWNrZ3JvdW5kLWNvbG9yOiB3aGl0ZTtcbn1cblxuYSB7XG4gIGNvbG9yOiAjNWNiODVjO1xuICBmb250LXdlaWdodDogYm9sZDtcbiAgZm9udC1zaXplOiA0dncgNHZoO1xuICB0ZXh0LWRlY29yYXRpb246IG5vbmU7XG59XG5cbmJ1dHRvbjpob3ZlciB7XG4gIGNvbG9yOiBibGFjaztcbn1cblxuc3BhbiB7XG4gIG1hcmdpbi1yaWdodDogMyU7XG59XG5cbiNsb2dvIHtcbiAgb2JqZWN0LWZpdDogY292ZXI7XG4gIGJhY2tncm91bmQtaW1hZ2U6IHVybChcIi4uLy4uL2Fzc2V0cy9sb2dvLmpwZ1wiKTtcbiAgYmFja2dyb3VuZC1zaXplOiBjb3ZlcjtcbiAgYm9yZGVyOiBibGFjaztcbn1cblxuLmF2YXRhciB7XG4gIGJvcmRlcjogYmxhY2s7XG4gIG9iamVjdC1maXQ6IGZpbGw7XG4gIGJhY2tncm91bmQtaW1hZ2U6IHVybCgnLi4vLi4vYXNzZXRzL3VzZXIucG5nJyk7XG4gIGJhY2tncm91bmQtc2l6ZTogY292ZXI7XG59XG5cbi5uYXYtc3BhY2VyIHtcbiAgZmxleDogMSAxIGF1dG87XG59XG5cbi5tYXQtc2lkZW5hdi1jb250YWluZXJ7XG4gIG1pbi1oZWlnaHQ6IDgwdmggIWltcG9ydGFudDtcbn1cblxubWF0LXNpZGVuYXYge1xuICB3aWR0aDogMzB2dztcbiAgLyogYmFja2dyb3VuZDogcmdiYSgwLCAyNTUsIDEwNiwgMC42KTsgKi9cbiAgLyogYmFja2dyb3VuZDpyZ2JhKDI1NSwgMjU1LCAyNTUsIDAuMyk7ICovXG4gIGJhY2tncm91bmQ6IHdoaXRlO1xuICBiYWNrZ3JvdW5kLXNpemU6IGNvdmVyO1xufVxuXG5idXR0b246dmlzaXRlZCB7XG4gIGNvbG9yOiBibGFjaztcbn1cblxuYnV0dG9uOmZvY3VzIHtcbiAgY29sb3I6IGJsYWNrO1xufSJdfQ== */"

/***/ }),

/***/ "./src/app/header/header.component.html":
/*!**********************************************!*\
  !*** ./src/app/header/header.component.html ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div>\n  <mat-toolbar>\n    <nav fxshow=\"true\" fxHide.gt-xs=\"true\">\n      <button mat-icon-button (click)=\"sidenav.toggle()\">\n        <mat-icon>menu</mat-icon>\n      </button>\n    </nav>\n\n    <span mat-card-avatar id=\"logo\"></span>\n    <span><a routerLink=\"/\">Knowledge Vault</a></span>\n    <span class=\"nav-fill-remaining-space\"></span>\n    <span class=\"align-center\"></span>\n    <span class=\"nav-spacer\"></span>\n\n    <div fxShow=\"true\" fxHide.lt-sm=\"true\">\n      <span *ngIf=\"!userThere; else elseBlock;\">\n        <button mat-button (click)=\"home()\">Home</button>\n        <button mat-button (click)=\"login()\">Login</button>\n        <button mat-button (click)=\"register()\">Register</button>\n      </span>\n      <ng-template #elseBlock>\n        <span style=\"color: green\" *ngIf=\"userThere; else elseBlock1;\"> Welcome {{ username }} </span>\n        <ng-template #elseBlock1>\n          <span> Welcome User </span>\n        </ng-template>\n        <a mat-menu [matMenuTriggerFor]=\"avatarMenu\">\n          <button mat-card-avatar class=\"avatar\"></button>\n        </a>\n        <mat-menu #avatarMenu=\"matMenu\">\n          <button mat-menu-item>Profile</button>\n          <button mat-menu-item>Setting</button>\n          <button mat-button (click)=\"logout()\"> Logout </button>\n          <button mat-menu-item>Help</button>\n        </mat-menu>\n      </ng-template>\n    </div>\n  </mat-toolbar>\n\n  <mat-sidenav-container fxFlexFill class=\"example-container\">\n    <mat-sidenav color=\"primary\" #sidenav fxLayout=\"column\" mode=\"over\" opened=\"false\" fxHide.gt-sm=\"true\">\n      <div fxLayout=\"column\">\n        <nav *ngIf=\"!userThere; else elseBlock;\">\n          <button mat-button (click)=\"home()\">Home</button>\n          <br>\n          <button mat-button (click)=\"login()\">Login</button>\n          <br>\n          <button mat-button (click)=\"register()\">Register</button>\n        </nav>\n        <ng-template #elseBlock>\n          <span style=\"color: green\" *ngIf=\"userThere; else elseBlock1;\"> Welcome {{ username }} </span>\n          <ng-template #elseBlock1>\n            <span> Welcome User </span>\n          </ng-template>\n          <a mat-menu [matMenuTriggerFor]=\"avatarMenu\">\n            <button mat-card-avatar class=\"avatar\"></button>\n          </a>\n          <mat-menu #avatarMenu=\"matMenu\">\n            <button mat-menu-item>Profile</button>\n            <button mat-menu-item>Setting</button>\n            <button mat-button (click)=\"logout()\"> Logout </button>\n            <button mat-menu-item>Help</button>\n          </mat-menu>\n        </ng-template>\n      </div>\n    </mat-sidenav>\n    <router-outlet></router-outlet>\n  </mat-sidenav-container>\n</div>"

/***/ }),

/***/ "./src/app/header/header.component.ts":
/*!********************************************!*\
  !*** ./src/app/header/header.component.ts ***!
  \********************************************/
/*! exports provided: HeaderComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HeaderComponent", function() { return HeaderComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _models__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../_models */ "./src/app/_models/index.ts");
/* harmony import */ var _services__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../_services */ "./src/app/_services/index.ts");
/* harmony import */ var _share_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../share.service */ "./src/app/share.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var HeaderComponent = /** @class */ (function () {
    /**
     * private constructor to inject other components and/or services.
     * @param router the router object to help navigate to a different route.
     * @param userService the service typescript class to get list of all Users stored in the database.
     * @param srvc the service typescript class to share data from one component to another
     * component irrespective of their relationship
     */
    function HeaderComponent(router, srvc) {
        var _this = this;
        this.router = router;
        this.srvc = srvc;
        this.username = 'User';
        this.userThere = false;
        this.srvc.getValue()
            .subscribe(function (data) { return _this.amILoggedOut = data; });
        var name = localStorage.getItem('userdata');
        console.log(name);
        if (name !== undefined && name != null) {
            this.username = name;
            this.userThere = true;
        }
    }
    HeaderComponent.prototype.ngOnInit = function () {
        // this.amILoggedOut = true;
    };
    /**
     * this function provides the routing for home component
     */
    HeaderComponent.prototype.home = function () {
        this.router.navigate(['']);
    };
    /**
     * this function provides the routing for login component
     */
    HeaderComponent.prototype.login = function () {
        console.log('sending the flag from header button...');
        this.srvc.setValue(this.amILoggedOut);
        this.router.navigate(['login']);
    };
    /**
     * this function provides the routing for register component
     */
    HeaderComponent.prototype.register = function () {
        this.router.navigate(['register']);
    };
    /**
     * this function provides the routing for logout component
     */
    HeaderComponent.prototype.logout = function () {
        this.amILoggedOut = true;
        localStorage.removeItem('currentuser');
        localStorage.removeItem('userdata');
        this.userThere = false;
        this.router.navigate(['home']);
    };
    HeaderComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-header',
            template: __webpack_require__(/*! ./header.component.html */ "./src/app/header/header.component.html"),
            styles: [__webpack_require__(/*! ./header.component.css */ "./src/app/header/header.component.css")],
            providers: [_models__WEBPACK_IMPORTED_MODULE_2__["User"], _services__WEBPACK_IMPORTED_MODULE_3__["UserService"]]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"], _share_service__WEBPACK_IMPORTED_MODULE_4__["ShareService"]])
    ], HeaderComponent);
    return HeaderComponent;
}());



/***/ }),

/***/ "./src/app/home/home.component.css":
/*!*****************************************!*\
  !*** ./src/app/home/home.component.css ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".home-page .banner {\n  background: #5cb85c;\n  box-sizing: inherit;\n}\n\n.banner {\n  color: #fff;\n  padding: 2rem;\n  margin-bottom: 2rem;\n  text-align: center;\n  font-family: source sans pro, sans-serif;\n  font-size: 1.5rem;\n  line-height: 1.5rem;\n}\n\n.logo-font {\n  font-size: 4vw;\n}\n\np {\n  font-size: 2vw;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvaG9tZS9ob21lLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDRSxvQkFBb0I7RUFDcEIsb0JBQW9CO0NBQ3JCOztBQUVEO0VBQ0UsWUFBWTtFQUNaLGNBQWM7RUFDZCxvQkFBb0I7RUFDcEIsbUJBQW1CO0VBQ25CLHlDQUF5QztFQUN6QyxrQkFBa0I7RUFDbEIsb0JBQW9CO0NBQ3JCOztBQUVEO0VBQ0UsZUFBZTtDQUNoQjs7QUFFRDtFQUNFLGVBQWU7Q0FDaEIiLCJmaWxlIjoic3JjL2FwcC9ob21lL2hvbWUuY29tcG9uZW50LmNzcyIsInNvdXJjZXNDb250ZW50IjpbIi5ob21lLXBhZ2UgLmJhbm5lciB7XG4gIGJhY2tncm91bmQ6ICM1Y2I4NWM7XG4gIGJveC1zaXppbmc6IGluaGVyaXQ7XG59XG5cbi5iYW5uZXIge1xuICBjb2xvcjogI2ZmZjtcbiAgcGFkZGluZzogMnJlbTtcbiAgbWFyZ2luLWJvdHRvbTogMnJlbTtcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xuICBmb250LWZhbWlseTogc291cmNlIHNhbnMgcHJvLCBzYW5zLXNlcmlmO1xuICBmb250LXNpemU6IDEuNXJlbTtcbiAgbGluZS1oZWlnaHQ6IDEuNXJlbTtcbn1cblxuLmxvZ28tZm9udCB7XG4gIGZvbnQtc2l6ZTogNHZ3O1xufVxuXG5wIHtcbiAgZm9udC1zaXplOiAydnc7XG59Il19 */"

/***/ }),

/***/ "./src/app/home/home.component.html":
/*!******************************************!*\
  !*** ./src/app/home/home.component.html ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"home-page\">\n  <div class=\"banner\">\n    <div class=\"container\">\n      <h1 class=\"logo-font\">Knowledge Vault\n        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n        <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'>\n      </h1>\n      <p>A place to gain <i>limitless</i> knowledge.</p>\n    </div>\n  </div>\n  <app-user-query></app-user-query>\n</div>"

/***/ }),

/***/ "./src/app/home/home.component.ts":
/*!****************************************!*\
  !*** ./src/app/home/home.component.ts ***!
  \****************************************/
/*! exports provided: HomeComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HomeComponent", function() { return HomeComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var HomeComponent = /** @class */ (function () {
    function HomeComponent() {
    }
    HomeComponent.prototype.ngOnInit = function () {
    };
    HomeComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-home',
            template: __webpack_require__(/*! ./home.component.html */ "./src/app/home/home.component.html"),
            styles: [__webpack_require__(/*! ./home.component.css */ "./src/app/home/home.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], HomeComponent);
    return HomeComponent;
}());



/***/ }),

/***/ "./src/app/login.service.ts":
/*!**********************************!*\
  !*** ./src/app/login.service.ts ***!
  \**********************************/
/*! exports provided: LoginService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginService", function() { return LoginService; });
/* harmony import */ var _share_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./share.service */ "./src/app/share.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var httpOptions = {
    headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({
        'Content-Type': 'application/json',
    })
};
var LoginService = /** @class */ (function () {
    function LoginService(http, router, srvc) {
        this.http = http;
        this.router = router;
        this.srvc = srvc;
        this.loginUrl = 'http://localhost:8184/user/login';
        this.validateURL = 'http://localhost:8184/secure/user/';
    }
    LoginService.prototype.login = function (user) {
        this.http.post(this.loginUrl, user, httpOptions).subscribe(function (data) {
            localStorage.setItem('currentuser', data.token);
            localStorage.setItem('userdata', data.username);
        });
    };
    LoginService.prototype.validateUser = function (user) {
        this.login(user);
        var url = this.validateURL + user.username;
        console.log(localStorage.getItem('userdata'));
        var httpoption = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('currentuser')
            })
        };
        return this.http.get(url, httpoption);
    };
    LoginService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_3__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"], _share_service__WEBPACK_IMPORTED_MODULE_0__["ShareService"]])
    ], LoginService);
    return LoginService;
}());



/***/ }),

/***/ "./src/app/login/login.component.css":
/*!*******************************************!*\
  !*** ./src/app/login/login.component.css ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".login-card {\n  background-color: white;\n  max-width: 80rem;\n  position: inherit;\n  box-shadow: none;\n  justify-content: center;\n  text-align: center;\n}\n\nbutton {\n  background-color: #5cb85c;\n  color: white;\n  border: none;\n  text-align: center;\n  text-decoration: none;\n  display: inline-block;\n  justify-content: center;\n\n}\n\n.login-form {\n  width: 100%;\n}\n\n.center{\n  width: 75%;\n  margin: 10px auto;\n}\n\n.login{\n  height: 80vh;\n  display: flex;\n  justify-content: center;\n  align-items: center;\n}\n\nmat-card-header {\n  justify-content: center;\n  color: #5cb85c;\n}\n\n.login-full-width {\n  width: 50vh;\n}\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbG9naW4vbG9naW4uY29tcG9uZW50LmNzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNFLHdCQUF3QjtFQUN4QixpQkFBaUI7RUFDakIsa0JBQWtCO0VBQ2xCLGlCQUFpQjtFQUNqQix3QkFBd0I7RUFDeEIsbUJBQW1CO0NBQ3BCOztBQUVEO0VBQ0UsMEJBQTBCO0VBQzFCLGFBQWE7RUFDYixhQUFhO0VBQ2IsbUJBQW1CO0VBQ25CLHNCQUFzQjtFQUN0QixzQkFBc0I7RUFDdEIsd0JBQXdCOztDQUV6Qjs7QUFFRDtFQUNFLFlBQVk7Q0FDYjs7QUFFRDtFQUNFLFdBQVc7RUFDWCxrQkFBa0I7Q0FDbkI7O0FBRUQ7RUFDRSxhQUFhO0VBQ2IsY0FBYztFQUNkLHdCQUF3QjtFQUN4QixvQkFBb0I7Q0FDckI7O0FBRUQ7RUFDRSx3QkFBd0I7RUFDeEIsZUFBZTtDQUNoQjs7QUFFRDtFQUNFLFlBQVk7Q0FDYiIsImZpbGUiOiJzcmMvYXBwL2xvZ2luL2xvZ2luLmNvbXBvbmVudC5jc3MiLCJzb3VyY2VzQ29udGVudCI6WyIubG9naW4tY2FyZCB7XG4gIGJhY2tncm91bmQtY29sb3I6IHdoaXRlO1xuICBtYXgtd2lkdGg6IDgwcmVtO1xuICBwb3NpdGlvbjogaW5oZXJpdDtcbiAgYm94LXNoYWRvdzogbm9uZTtcbiAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XG4gIHRleHQtYWxpZ246IGNlbnRlcjtcbn1cblxuYnV0dG9uIHtcbiAgYmFja2dyb3VuZC1jb2xvcjogIzVjYjg1YztcbiAgY29sb3I6IHdoaXRlO1xuICBib3JkZXI6IG5vbmU7XG4gIHRleHQtYWxpZ246IGNlbnRlcjtcbiAgdGV4dC1kZWNvcmF0aW9uOiBub25lO1xuICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XG4gIGp1c3RpZnktY29udGVudDogY2VudGVyO1xuXG59XG5cbi5sb2dpbi1mb3JtIHtcbiAgd2lkdGg6IDEwMCU7XG59XG5cbi5jZW50ZXJ7XG4gIHdpZHRoOiA3NSU7XG4gIG1hcmdpbjogMTBweCBhdXRvO1xufVxuXG4ubG9naW57XG4gIGhlaWdodDogODB2aDtcbiAgZGlzcGxheTogZmxleDtcbiAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XG4gIGFsaWduLWl0ZW1zOiBjZW50ZXI7XG59XG5cbm1hdC1jYXJkLWhlYWRlciB7XG4gIGp1c3RpZnktY29udGVudDogY2VudGVyO1xuICBjb2xvcjogIzVjYjg1Yztcbn1cblxuLmxvZ2luLWZ1bGwtd2lkdGgge1xuICB3aWR0aDogNTB2aDtcbn1cbiJdfQ== */"

/***/ }),

/***/ "./src/app/login/login.component.html":
/*!********************************************!*\
  !*** ./src/app/login/login.component.html ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"login\">\n  <mat-card class=\"login-card\">\n    <div class=\"login-content\">\n      <mat-card-header>\n        <mat-card-title>Login</mat-card-title>\n      </mat-card-header>\n      <mat-card-content>\n        <form class=\"login-form\">\n          <table class=\"login-full-width\" cellspacing=\"0\">\n            <tr>\n              <td>\n                <mat-form-field class=\"login-full-width\">\n                <input matInput placeholder=\"Username\" [(ngModel)]=\"username\" name=\"username\" required>\n                </mat-form-field>\n              </td>\n            </tr>\n            <tr>\n            <td><mat-form-field class=\"login-full-width\">\n              <input matInput placeholder=\"Password\" [(ngModel)]=\"password\"type=\"password\" name=\"password\" required>\n            </mat-form-field></td>\n          </tr></table>\n        </form>\n        <mat-spinner [style.display]=\"showSpinner ? 'block' : 'none'\"></mat-spinner>\n      </mat-card-content>\n      <mat-card-actions>\n        <button mat-raised-button (click)=\"login()\">Login</button>\n      </mat-card-actions>\n    </div>\n  </mat-card>\n</div>\n\n <!-- \n   <h2>Login</h2>\n<form [formGroup]=\"loginForm\" (ngSubmit)=\"onSubmit()\">\n    <div class=\"form-group\">\n        <label for=\"username\">Username</label>\n        <input type=\"text\" formControlName=\"username\" class=\"form-control\" [ngClass]=\"{ 'is-invalid': submitted && f.username.errors }\" />\n        <div *ngIf=\"submitted && f.username.errors\" class=\"invalid-feedback\">\n            <div *ngIf=\"f.username.errors.required\">Username is required</div>\n        </div>\n    </div>\n    <div class=\"form-group\">\n        <label for=\"password\">Password</label>\n        <input type=\"password\" formControlName=\"password\" class=\"form-control\" [ngClass]=\"{ 'is-invalid': submitted && f.password.errors }\" />\n        <div *ngIf=\"submitted && f.password.errors\" class=\"invalid-feedback\">\n            <div *ngIf=\"f.password.errors.required\">Password is required</div>\n        </div>\n    </div>\n    <div class=\"form-group\">\n        <button [disabled]=\"loading\" class=\"btn btn-primary\">Login</button>\n        <img *ngIf=\"loading\" src=\"data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==\" />\n        <a [routerLink]=\"['/register']\" class=\"btn btn-link\">Register</a>\n    </div>\n</form>\n\n  -->"

/***/ }),

/***/ "./src/app/login/login.component.ts":
/*!******************************************!*\
  !*** ./src/app/login/login.component.ts ***!
  \******************************************/
/*! exports provided: LoginComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginComponent", function() { return LoginComponent; });
/* harmony import */ var _login_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./../login.service */ "./src/app/login.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _share_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../share.service */ "./src/app/share.service.ts");
/* harmony import */ var _models_auth_user__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../models/auth/user */ "./src/app/models/auth/user.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var LoginComponent = /** @class */ (function () {
    function LoginComponent(router, srvc, loginsrvc) {
        var _this = this;
        this.router = router;
        this.srvc = srvc;
        this.loginsrvc = loginsrvc;
        this.srvc.getValue()
            .subscribe(function (val) {
            _this.logInStatus = !val;
        });
    }
    LoginComponent.prototype.ngOnInit = function () { };
    LoginComponent.prototype.bhejdo = function () {
        this.srvc.setValue(this.logInStatus);
    };
    LoginComponent.prototype.login = function () {
        var _this = this;
        console.log('getting the flag value before actually logging in');
        var user = new _models_auth_user__WEBPACK_IMPORTED_MODULE_4__["User"](this.username, this.password);
        this.loginsrvc.validateUser(user)
            .subscribe(function (data) {
            if (data.username === _this.username) {
                _this.bhejdo();
                if (data.role === 'General User') {
                    _this.router.navigate(['sme']);
                }
                else {
                    _this.router.navigate(['user']);
                }
                window.location.reload();
            }
            else {
                alert('Invalid Credentials');
            }
        });
    };
    LoginComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["Component"])({
            selector: 'app-login',
            template: __webpack_require__(/*! ./login.component.html */ "./src/app/login/login.component.html"),
            styles: [__webpack_require__(/*! ./login.component.css */ "./src/app/login/login.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"], _share_service__WEBPACK_IMPORTED_MODULE_3__["ShareService"], _login_service__WEBPACK_IMPORTED_MODULE_0__["LoginService"]])
    ], LoginComponent);
    return LoginComponent;
}());



/***/ }),

/***/ "./src/app/models/auth/user.ts":
/*!*************************************!*\
  !*** ./src/app/models/auth/user.ts ***!
  \*************************************/
/*! exports provided: User */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "User", function() { return User; });
var User = /** @class */ (function () {
    function User(username, password) {
        this.username = username;
        this.password = password;
    }
    return User;
}());



/***/ }),

/***/ "./src/app/models/paragraph.ts":
/*!*************************************!*\
  !*** ./src/app/models/paragraph.ts ***!
  \*************************************/
/*! exports provided: Paragraph */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Paragraph", function() { return Paragraph; });
var Paragraph = /** @class */ (function () {
    function Paragraph() {
    }
    return Paragraph;
}());



/***/ }),

/***/ "./src/app/models/receivedQuery.ts":
/*!*****************************************!*\
  !*** ./src/app/models/receivedQuery.ts ***!
  \*****************************************/
/*! exports provided: ReceivedQuery */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReceivedQuery", function() { return ReceivedQuery; });
var ReceivedQuery = /** @class */ (function () {
    function ReceivedQuery(cond, symp, struc, para) {
        this.MedicalCondition = cond;
        this.MedicalSymptoms = symp;
        this.AnatomicalStructures = struc;
        this.Paragraphs = para;
    }
    return ReceivedQuery;
}());



/***/ }),

/***/ "./src/app/models/reg/userdetails.ts":
/*!*******************************************!*\
  !*** ./src/app/models/reg/userdetails.ts ***!
  \*******************************************/
/*! exports provided: UserDetails */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserDetails", function() { return UserDetails; });
var UserDetails = /** @class */ (function () {
    function UserDetails(fn, ln, un, r, pwd) {
        this.firstname = fn;
        this.lastname = ln;
        this.username = un;
        this.role = r;
        this.password = pwd;
    }
    return UserDetails;
}());



/***/ }),

/***/ "./src/app/profile/general-user/general-user.component.css":
/*!*****************************************************************!*\
  !*** ./src/app/profile/general-user/general-user.component.css ***!
  \*****************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".home-page .banner {\n  background: #5cb85c;\n  box-shadow: inset 0 8px 8px -8px rgba(0, 0, 0, .3), inset 0 -8px 8px -8px rgba(0, 0, 0, .3);\n  box-sizing: inherit;\n}\n\n.banner {\n  color: #fff;\n  padding: 2rem;\n  margin-bottom: 2rem;\n  text-align: center;\n  font-family: source sans pro, sans-serif;\n  font-size: 1.5rem;\n  line-height: 1.5rem;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcHJvZmlsZS9nZW5lcmFsLXVzZXIvZ2VuZXJhbC11c2VyLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDRSxvQkFBb0I7RUFDcEIsNEZBQTRGO0VBQzVGLG9CQUFvQjtDQUNyQjs7QUFFRDtFQUNFLFlBQVk7RUFDWixjQUFjO0VBQ2Qsb0JBQW9CO0VBQ3BCLG1CQUFtQjtFQUNuQix5Q0FBeUM7RUFDekMsa0JBQWtCO0VBQ2xCLG9CQUFvQjtDQUNyQiIsImZpbGUiOiJzcmMvYXBwL3Byb2ZpbGUvZ2VuZXJhbC11c2VyL2dlbmVyYWwtdXNlci5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmhvbWUtcGFnZSAuYmFubmVyIHtcbiAgYmFja2dyb3VuZDogIzVjYjg1YztcbiAgYm94LXNoYWRvdzogaW5zZXQgMCA4cHggOHB4IC04cHggcmdiYSgwLCAwLCAwLCAuMyksIGluc2V0IDAgLThweCA4cHggLThweCByZ2JhKDAsIDAsIDAsIC4zKTtcbiAgYm94LXNpemluZzogaW5oZXJpdDtcbn1cblxuLmJhbm5lciB7XG4gIGNvbG9yOiAjZmZmO1xuICBwYWRkaW5nOiAycmVtO1xuICBtYXJnaW4tYm90dG9tOiAycmVtO1xuICB0ZXh0LWFsaWduOiBjZW50ZXI7XG4gIGZvbnQtZmFtaWx5OiBzb3VyY2Ugc2FucyBwcm8sIHNhbnMtc2VyaWY7XG4gIGZvbnQtc2l6ZTogMS41cmVtO1xuICBsaW5lLWhlaWdodDogMS41cmVtO1xufSJdfQ== */"

/***/ }),

/***/ "./src/app/profile/general-user/general-user.component.html":
/*!******************************************************************!*\
  !*** ./src/app/profile/general-user/general-user.component.html ***!
  \******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"home-page\">\n  <div class=\"banner\">\n    <div class=\"container\">\n      <h1>Welcome, User!</h1>\n      <h3 class=\"logo-font\">Knowledge Vault\n      <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n      <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'>\n      </h3>\n      <p>A place to gain <i>limitless</i> knowledge.</p>\n    </div>\n  </div>\n</div>"

/***/ }),

/***/ "./src/app/profile/general-user/general-user.component.ts":
/*!****************************************************************!*\
  !*** ./src/app/profile/general-user/general-user.component.ts ***!
  \****************************************************************/
/*! exports provided: GeneralUserComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GeneralUserComponent", function() { return GeneralUserComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var GeneralUserComponent = /** @class */ (function () {
    function GeneralUserComponent() {
    }
    GeneralUserComponent.prototype.ngOnInit = function () {
    };
    GeneralUserComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-general-user',
            template: __webpack_require__(/*! ./general-user.component.html */ "./src/app/profile/general-user/general-user.component.html"),
            styles: [__webpack_require__(/*! ./general-user.component.css */ "./src/app/profile/general-user/general-user.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], GeneralUserComponent);
    return GeneralUserComponent;
}());



/***/ }),

/***/ "./src/app/profile/profile.module.ts":
/*!*******************************************!*\
  !*** ./src/app/profile/profile.module.ts ***!
  \*******************************************/
/*! exports provided: ProfileModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProfileModule", function() { return ProfileModule; });
/* harmony import */ var _core_material_module__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./../core/material.module */ "./src/app/core/material.module.ts");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _general_user_general_user_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./general-user/general-user.component */ "./src/app/profile/general-user/general-user.component.ts");
/* harmony import */ var _sme_sme_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./sme/sme.module */ "./src/app/profile/sme/sme.module.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};






var ProfileModule = /** @class */ (function () {
    function ProfileModule() {
    }
    ProfileModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [_general_user_general_user_component__WEBPACK_IMPORTED_MODULE_3__["GeneralUserComponent"]],
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_5__["FormsModule"],
                _core_material_module__WEBPACK_IMPORTED_MODULE_0__["CustomMaterialModule"],
                _sme_sme_module__WEBPACK_IMPORTED_MODULE_4__["SmeModule"]
            ]
        })
    ], ProfileModule);
    return ProfileModule;
}());



/***/ }),

/***/ "./src/app/profile/sme/dragndrop/dragndrop.component.css":
/*!***************************************************************!*\
  !*** ./src/app/profile/sme/dragndrop/dragndrop.component.css ***!
  \***************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".saveButton {\n    background-color: lightcoral;\n    margin-left: 40%;\n}\n\n#myImg {\n    background-image: url('dropArea.jpg');\n    background-size: cover;\n    background-repeat: no-repeat;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcHJvZmlsZS9zbWUvZHJhZ25kcm9wL2RyYWduZHJvcC5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0lBQ0ksNkJBQTZCO0lBQzdCLGlCQUFpQjtDQUNwQjs7QUFFRDtJQUNJLHNDQUF5RDtJQUN6RCx1QkFBdUI7SUFDdkIsNkJBQTZCO0NBQ2hDIiwiZmlsZSI6InNyYy9hcHAvcHJvZmlsZS9zbWUvZHJhZ25kcm9wL2RyYWduZHJvcC5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLnNhdmVCdXR0b24ge1xuICAgIGJhY2tncm91bmQtY29sb3I6IGxpZ2h0Y29yYWw7XG4gICAgbWFyZ2luLWxlZnQ6IDQwJTtcbn1cblxuI215SW1nIHtcbiAgICBiYWNrZ3JvdW5kLWltYWdlOiB1cmwoXCIuLi8uLi8uLi8uLi9hc3NldHMvZHJvcEFyZWEuanBnXCIpO1xuICAgIGJhY2tncm91bmQtc2l6ZTogY292ZXI7XG4gICAgYmFja2dyb3VuZC1yZXBlYXQ6IG5vLXJlcGVhdDtcbn0iXX0= */"

/***/ }),

/***/ "./src/app/profile/sme/dragndrop/dragndrop.component.html":
/*!****************************************************************!*\
  !*** ./src/app/profile/sme/dragndrop/dragndrop.component.html ***!
  \****************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-card>\n  <mat-card-title style=\"background-color:grey;width: 100%;\">\n    <legend style=\"text-align:center\"><b>Upload Document</b></legend>\n  </mat-card-title>\n  <img mat-card-image id=\"myImg\">\n    <mat-card-content>\n      <app-file-upload-drag-drop [IsuploadSucess]=\"IsUploaded\" maxFiles=\"10\" maxSize=\"10\" fileExt=\"PDF\" (uploadStatus)=\"getFiles($event)\">\n      </app-file-upload-drag-drop>\n    </mat-card-content>\n</mat-card>\n\n<button class=\"saveButton\" mat-raised-button type=\"button\" (click)=\"sendFileDB()\">Save Document</button>\n\n<div *ngIf=\"show_success_msg\">\n  <h1 style=\"color: mediumpurple\"> {{ save_success }} </h1>\n</div>\n\n<br>"

/***/ }),

/***/ "./src/app/profile/sme/dragndrop/dragndrop.component.ts":
/*!**************************************************************!*\
  !*** ./src/app/profile/sme/dragndrop/dragndrop.component.ts ***!
  \**************************************************************/
/*! exports provided: DragndropComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DragndropComponent", function() { return DragndropComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var DragndropComponent = /** @class */ (function () {
    function DragndropComponent() {
        this.fileList = [];
        this.show_success_msg = false;
        this.formData = new FormData();
        this.IsUploaded = false;
    }
    DragndropComponent.prototype.ngOnInit = function () { };
    //#region file upload drag and drop on intake page
    DragndropComponent.prototype.sendFileDB = function () {
        this.IsUploaded = false;
        this.show_success_msg = true;
        this.save_success = "Thank you for your contribution.We have saved your document in our database.We will add it to our knowledge-base once it is approved by Paurush Chaudhary.";
        if (this.fileList.length > 0) {
            for (var i = 0; i < this.fileList.length; i++) {
                this.FileName = this.fileList[i].name;
                this.formData.append('File', this.fileList[i]);
                // append more item in FormData and send to server
                // call service to send file on server via FormData.
                // if file saved in DB set true.
                // this.IsUploaded = true;
            }
        }
    };
    DragndropComponent.prototype.getFiles = function (files) {
        // all file are avilable here.you can customize according your rquirment.
        if (files !== false && files !== undefined && files != null && files !== '' && files.length > 0) {
            this.fileList = files;
        }
        else if (files == null) {
            this.fileList = [];
            return;
        }
    };
    DragndropComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-dragndrop',
            template: __webpack_require__(/*! ./dragndrop.component.html */ "./src/app/profile/sme/dragndrop/dragndrop.component.html"),
            styles: [__webpack_require__(/*! ./dragndrop.component.css */ "./src/app/profile/sme/dragndrop/dragndrop.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], DragndropComponent);
    return DragndropComponent;
}());



/***/ }),

/***/ "./src/app/profile/sme/file-upload-drag-drop/file-upload-drag-drop.component.css":
/*!***************************************************************************************!*\
  !*** ./src/app/profile/sme/file-upload-drag-drop/file-upload-drag-drop.component.css ***!
  \***************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".error {\n    color: #f00;\n}\n\n.success {\n    color: green;\n}\n\n.dragarea {\n    font-size: 20px;\n    padding: 24px 20px 24px 20px;\n    background-color: #fff;\n    color: #ccc;\n}\n\n.drop {\n    background-color: blueviolet;\n}\n\n.droparea {\n    font-size: 20px;\n    padding: 24px 20px 24px 20px;\n    background-color: #f2f2f2;\n    color: #ccc;\n}\n\n.successfiles li {\n    padding: 2px;\n}\n\n.cursorpointer {\n    cursor: pointer;\n}\n\na {\n    color: rgb(33, 150, 243);\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcHJvZmlsZS9zbWUvZmlsZS11cGxvYWQtZHJhZy1kcm9wL2ZpbGUtdXBsb2FkLWRyYWctZHJvcC5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0lBQ0ksWUFBWTtDQUNmOztBQUVEO0lBQ0ksYUFBYTtDQUNoQjs7QUFFRDtJQUNJLGdCQUFnQjtJQUNoQiw2QkFBNkI7SUFDN0IsdUJBQXVCO0lBQ3ZCLFlBQVk7Q0FDZjs7QUFFRDtJQUNJLDZCQUE2QjtDQUNoQzs7QUFDRDtJQUNJLGdCQUFnQjtJQUNoQiw2QkFBNkI7SUFDN0IsMEJBQTBCO0lBQzFCLFlBQVk7Q0FDZjs7QUFFRDtJQUNJLGFBQWE7Q0FDaEI7O0FBRUQ7SUFDSSxnQkFBZ0I7Q0FDbkI7O0FBRUQ7SUFDSSx5QkFBeUI7Q0FDNUIiLCJmaWxlIjoic3JjL2FwcC9wcm9maWxlL3NtZS9maWxlLXVwbG9hZC1kcmFnLWRyb3AvZmlsZS11cGxvYWQtZHJhZy1kcm9wLmNvbXBvbmVudC5jc3MiLCJzb3VyY2VzQ29udGVudCI6WyIuZXJyb3Ige1xuICAgIGNvbG9yOiAjZjAwO1xufVxuXG4uc3VjY2VzcyB7XG4gICAgY29sb3I6IGdyZWVuO1xufVxuXG4uZHJhZ2FyZWEge1xuICAgIGZvbnQtc2l6ZTogMjBweDtcbiAgICBwYWRkaW5nOiAyNHB4IDIwcHggMjRweCAyMHB4O1xuICAgIGJhY2tncm91bmQtY29sb3I6ICNmZmY7XG4gICAgY29sb3I6ICNjY2M7XG59XG5cbi5kcm9wIHtcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiBibHVldmlvbGV0O1xufVxuLmRyb3BhcmVhIHtcbiAgICBmb250LXNpemU6IDIwcHg7XG4gICAgcGFkZGluZzogMjRweCAyMHB4IDI0cHggMjBweDtcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjZjJmMmYyO1xuICAgIGNvbG9yOiAjY2NjO1xufVxuXG4uc3VjY2Vzc2ZpbGVzIGxpIHtcbiAgICBwYWRkaW5nOiAycHg7XG59XG5cbi5jdXJzb3Jwb2ludGVyIHtcbiAgICBjdXJzb3I6IHBvaW50ZXI7XG59XG5cbmEge1xuICAgIGNvbG9yOiByZ2IoMzMsIDE1MCwgMjQzKTtcbn0iXX0= */"

/***/ }),

/***/ "./src/app/profile/sme/file-upload-drag-drop/file-upload-drag-drop.component.html":
/*!****************************************************************************************!*\
  !*** ./src/app/profile/sme/file-upload-drag-drop/file-upload-drag-drop.component.html ***!
  \****************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-card-content class=\"drop\">\n    <div draggable=\"true\" ngClass=\"{{dragAreaClass}}\">\n        <div class=\"row\">\n            <div class=\"col-md-12 text-center\">\n                <div style=\"margin-left: 25%\">\n                    <a href=\"javascript:void(0)\" (click)=\"file.click()\"> Click to browse </a> Or Drag & Drop File.\n                </div>\n                <input type=\"file\" #file [multiple]=\"(maxFiles >=1)\" (change)=\"onFileChange($event)\" style=\"display:none\" />\n            </div>\n        </div>\n    </div>\n</mat-card-content>\n\n<mat-card-footer>\n    <div *ngIf=\"errors.length > 0 || successfiles.length > 0\" class=\"pull-right cursorpointer\" (click)=\"clearFiles()\">\n        Clear All</div>\n    <div class=\"row error\" *ngIf=\"errors.length > 0\">\n        <ul>\n            <li *ngFor=\"let err of errors\">{{err}}</li>\n        </ul>\n    </div>\n    <div class=\"row success successfiles\" *ngIf=\"successfiles.length > 0\">\n        <ul>\n            <li *ngFor=\"let succ of successfiles\">{{succ}}</li>\n        </ul>\n    </div>\n</mat-card-footer>"

/***/ }),

/***/ "./src/app/profile/sme/file-upload-drag-drop/file-upload-drag-drop.component.ts":
/*!**************************************************************************************!*\
  !*** ./src/app/profile/sme/file-upload-drag-drop/file-upload-drag-drop.component.ts ***!
  \**************************************************************************************/
/*! exports provided: FileUploadDragDropComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FileUploadDragDropComponent", function() { return FileUploadDragDropComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var FileUploadDragDropComponent = /** @class */ (function () {
    function FileUploadDragDropComponent() {
        this.errors = [];
        this.successfiles = [];
        this.dragAreaClass = 'dragarea';
        this.fileList = [];
        this.fileExt = 'PDF';
        this.maxFiles = 10;
        this.maxSize = 10;
        this.uploadStatus = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
        this.successfiles = [];
    }
    Object.defineProperty(FileUploadDragDropComponent.prototype, "IsuploadSucess", {
        set: function (isUploaded) {
            if (isUploaded) {
                this.errors = [];
                this.successfiles = [];
                this.fileList = [];
            }
        },
        enumerable: true,
        configurable: true
    });
    FileUploadDragDropComponent.prototype.ngOnInit = function () { };
    FileUploadDragDropComponent.prototype.onFileChange = function (event) {
        var files = event.target.files;
        this.saveFiles(files);
    };
    FileUploadDragDropComponent.prototype.onDragOver = function (event) {
        this.dragAreaClass = 'droparea';
        event.preventDefault();
    };
    FileUploadDragDropComponent.prototype.onDragEnter = function (event) {
        this.dragAreaClass = 'droparea';
        event.preventDefault();
    };
    FileUploadDragDropComponent.prototype.onDragLeave = function (event) {
        this.dragAreaClass = 'dragarea';
        event.preventDefault();
    };
    FileUploadDragDropComponent.prototype.onDrop = function (event) {
        this.dragAreaClass = 'dragarea';
        event.preventDefault();
        event.stopPropagation();
        var files = event.dataTransfer.files;
        this.saveFiles(files);
    };
    FileUploadDragDropComponent.prototype.saveFiles = function (files) {
        this.errors = [];
        // Validate file size and allowed extensions
        if (files.length > 0 && (!this.isValidFiles(files))) {
            this.uploadStatus.emit(false);
            return;
        }
        if (files.length > 0 && (this.isValidFiles(files))) {
            var _loop_1 = function (j) {
                if (!this_1.fileList.some(function (x) { return x.name === files[j].name; })) {
                    this_1.fileList.push(files[j]);
                    this_1.successfiles.push('File: ' + files[j].name + ' added successfully.');
                }
                else {
                    this_1.errors.push('File: ' + files[j].name + ' Already added in list.');
                }
            };
            var this_1 = this;
            for (var j = 0; j < files.length; j++) {
                _loop_1(j);
            }
            this.uploadStatus.emit(this.fileList);
            return;
        }
    };
    FileUploadDragDropComponent.prototype.isValidFiles = function (files) {
        // Check Number of files
        if (files.length > this.maxFiles) {
            this.errors.push('Error: At a time you can upload only ' + this.maxFiles + ' files');
            return;
        }
        this.isValidFileExtension(files);
        return this.errors.length === 0;
    };
    FileUploadDragDropComponent.prototype.isValidFileExtension = function (files) {
        // Make array of file extensions
        var extensions = (this.fileExt.split(','))
            .map(function (x) { return x.toLocaleUpperCase().trim(); });
        for (var i = 0; i < files.length; i++) {
            // Get file extension
            var ext = files[i].name.toUpperCase().split('.').pop() || files[i].name;
            // Check the extension exists
            var exists = extensions.includes(ext);
            if (!exists) {
                this.errors.push('Invalid file : ' + files[i].name + ', Upload only ' + this.fileExt + ' file.');
            }
            this.isValidFileSize(files[i]);
        }
    };
    FileUploadDragDropComponent.prototype.isValidFileSize = function (file) {
        var fileSizeinMB = file.size / (1024 * 1000);
        var size = Math.round(fileSizeinMB * 100) / 100;
        if (size > this.maxSize) {
            this.errors.push('Error (File Size): ' + file.name + ': exceed file size limit of ' + this.maxSize + 'MB ( ' + size + 'MB )');
        }
    };
    FileUploadDragDropComponent.prototype.clearFiles = function () {
        this.errors = [];
        this.successfiles = [];
        this.fileList = [];
        this.uploadStatus.emit(null);
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Object)
    ], FileUploadDragDropComponent.prototype, "fileExt", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Object)
    ], FileUploadDragDropComponent.prototype, "maxFiles", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Object)
    ], FileUploadDragDropComponent.prototype, "maxSize", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"])(),
        __metadata("design:type", Object)
    ], FileUploadDragDropComponent.prototype, "uploadStatus", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Boolean),
        __metadata("design:paramtypes", [Boolean])
    ], FileUploadDragDropComponent.prototype, "IsuploadSucess", null);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["HostListener"])('dragover', ['$event']),
        __metadata("design:type", Function),
        __metadata("design:paramtypes", [Object]),
        __metadata("design:returntype", void 0)
    ], FileUploadDragDropComponent.prototype, "onDragOver", null);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["HostListener"])('dragenter', ['$event']),
        __metadata("design:type", Function),
        __metadata("design:paramtypes", [Object]),
        __metadata("design:returntype", void 0)
    ], FileUploadDragDropComponent.prototype, "onDragEnter", null);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["HostListener"])('dragleave', ['$event']),
        __metadata("design:type", Function),
        __metadata("design:paramtypes", [Object]),
        __metadata("design:returntype", void 0)
    ], FileUploadDragDropComponent.prototype, "onDragLeave", null);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["HostListener"])('drop', ['$event']),
        __metadata("design:type", Function),
        __metadata("design:paramtypes", [Object]),
        __metadata("design:returntype", void 0)
    ], FileUploadDragDropComponent.prototype, "onDrop", null);
    FileUploadDragDropComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-file-upload-drag-drop',
            template: __webpack_require__(/*! ./file-upload-drag-drop.component.html */ "./src/app/profile/sme/file-upload-drag-drop/file-upload-drag-drop.component.html"),
            styles: [__webpack_require__(/*! ./file-upload-drag-drop.component.css */ "./src/app/profile/sme/file-upload-drag-drop/file-upload-drag-drop.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], FileUploadDragDropComponent);
    return FileUploadDragDropComponent;
}());



/***/ }),

/***/ "./src/app/profile/sme/sme.component.css":
/*!***********************************************!*\
  !*** ./src/app/profile/sme/sme.component.css ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".home {\n    \n}\n\n.bg {\n    background-image: url('vault.jpg');\n    background-position: center;\n    background-repeat: no-repeat;\n    background-size: cover;\n    height: 200px;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcHJvZmlsZS9zbWUvc21lLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7O0NBRUM7O0FBRUQ7SUFDSSxtQ0FBbUQ7SUFDbkQsNEJBQTRCO0lBQzVCLDZCQUE2QjtJQUM3Qix1QkFBdUI7SUFDdkIsY0FBYztDQUNqQiIsImZpbGUiOiJzcmMvYXBwL3Byb2ZpbGUvc21lL3NtZS5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmhvbWUge1xuICAgIFxufVxuXG4uYmcge1xuICAgIGJhY2tncm91bmQtaW1hZ2U6IHVybChcIi4uLy4uLy4uL2Fzc2V0cy92YXVsdC5qcGdcIik7XG4gICAgYmFja2dyb3VuZC1wb3NpdGlvbjogY2VudGVyO1xuICAgIGJhY2tncm91bmQtcmVwZWF0OiBuby1yZXBlYXQ7XG4gICAgYmFja2dyb3VuZC1zaXplOiBjb3ZlcjtcbiAgICBoZWlnaHQ6IDIwMHB4O1xufSJdfQ== */"

/***/ }),

/***/ "./src/app/profile/sme/sme.component.html":
/*!************************************************!*\
  !*** ./src/app/profile/sme/sme.component.html ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"home\">\n  <h1 style=\"text-align:center;color: crimson\">\n    Welcome to the SME Landing page.Feel free to contribute to our knowledge-base.\n  </h1>\n  <div class =\"bg\"></div>\n  <app-dragndrop> </app-dragndrop>\n</div>\n\n"

/***/ }),

/***/ "./src/app/profile/sme/sme.component.ts":
/*!**********************************************!*\
  !*** ./src/app/profile/sme/sme.component.ts ***!
  \**********************************************/
/*! exports provided: SmeComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SmeComponent", function() { return SmeComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var SmeComponent = /** @class */ (function () {
    function SmeComponent() {
    }
    SmeComponent.prototype.ngOnInit = function () { };
    SmeComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-sme',
            template: __webpack_require__(/*! ./sme.component.html */ "./src/app/profile/sme/sme.component.html"),
            styles: [__webpack_require__(/*! ./sme.component.css */ "./src/app/profile/sme/sme.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], SmeComponent);
    return SmeComponent;
}());



/***/ }),

/***/ "./src/app/profile/sme/sme.module.ts":
/*!*******************************************!*\
  !*** ./src/app/profile/sme/sme.module.ts ***!
  \*******************************************/
/*! exports provided: SmeModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SmeModule", function() { return SmeModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var src_app_core_material_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/core/material.module */ "./src/app/core/material.module.ts");
/* harmony import */ var _sme_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./sme.component */ "./src/app/profile/sme/sme.component.ts");
/* harmony import */ var _file_upload_drag_drop_file_upload_drag_drop_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./file-upload-drag-drop/file-upload-drag-drop.component */ "./src/app/profile/sme/file-upload-drag-drop/file-upload-drag-drop.component.ts");
/* harmony import */ var _dragndrop_dragndrop_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./dragndrop/dragndrop.component */ "./src/app/profile/sme/dragndrop/dragndrop.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};






var SmeModule = /** @class */ (function () {
    function SmeModule() {
    }
    SmeModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            declarations: [_sme_component__WEBPACK_IMPORTED_MODULE_3__["SmeComponent"], _dragndrop_dragndrop_component__WEBPACK_IMPORTED_MODULE_5__["DragndropComponent"], _file_upload_drag_drop_file_upload_drag_drop_component__WEBPACK_IMPORTED_MODULE_4__["FileUploadDragDropComponent"]],
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                src_app_core_material_module__WEBPACK_IMPORTED_MODULE_2__["CustomMaterialModule"]
            ]
        })
    ], SmeModule);
    return SmeModule;
}());



/***/ }),

/***/ "./src/app/query-results/query-results.component.css":
/*!***********************************************************!*\
  !*** ./src/app/query-results/query-results.component.css ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".error {\n    color: red;\n    background: url('robot-error.webp') center center no-repeat;\n    background-size: cover;\n    text-align: center;\n    height: 600px;\n    width: 100%;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcXVlcnktcmVzdWx0cy9xdWVyeS1yZXN1bHRzLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7SUFDSSxXQUFXO0lBQ1gsNERBQXlFO0lBQ3pFLHVCQUF1QjtJQUN2QixtQkFBbUI7SUFDbkIsY0FBYztJQUNkLFlBQVk7Q0FDZiIsImZpbGUiOiJzcmMvYXBwL3F1ZXJ5LXJlc3VsdHMvcXVlcnktcmVzdWx0cy5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmVycm9yIHtcbiAgICBjb2xvcjogcmVkO1xuICAgIGJhY2tncm91bmQ6IHVybCgnLi4vLi4vYXNzZXRzL3JvYm90LWVycm9yLndlYnAnKSBjZW50ZXIgY2VudGVyIG5vLXJlcGVhdDtcbiAgICBiYWNrZ3JvdW5kLXNpemU6IGNvdmVyO1xuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcbiAgICBoZWlnaHQ6IDYwMHB4O1xuICAgIHdpZHRoOiAxMDAlO1xufSJdfQ== */"

/***/ }),

/***/ "./src/app/query-results/query-results.component.html":
/*!************************************************************!*\
  !*** ./src/app/query-results/query-results.component.html ***!
  \************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!-- {{dummy1.MedicalCondition}}<br>\n<div *ngFor=\"let symptom of dummy1.MedicalSymptoms\" >\n  {{symptom}}\n</div>\n<div *ngFor=\"let bodyPart of dummy1.AnatomicalStructures\" >\n  {{bodyPart}}\n</div>\n<div *ngFor=\"let paragraph of dummy1.Paragraphs\" >\n  {{paragraph.DocumentId}}<br>\n  {{paragraph.Content}}\n</div> -->\n\n<app-card [card]='dummy1' ></app-card>\n<!-- <nav *ngIf=\"queryResults == null; else elseBlock;\">\n  <nav>\n    <div class=\"error\"></div>\n  </nav>\n<nav> \n<ng-template #elseBlock>\n  <div *ngFor=\"let query of queryRes\" >!-->\n    <!-- <app-card [res]=\"query\"></app-card> -->\n    {{ query.Node1 }} <br>\n    {{ query.Node1Label }} <br>\n    {{ query.Relation }} <br>\n    {{ query.Node2 }} <br>\n    {{ query.Node2Label }} <br>\n    \n  </div>\n</ng-template>\n\n\n\n"

/***/ }),

/***/ "./src/app/query-results/query-results.component.ts":
/*!**********************************************************!*\
  !*** ./src/app/query-results/query-results.component.ts ***!
  \**********************************************************/
/*! exports provided: QueryResultsComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "QueryResultsComponent", function() { return QueryResultsComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _data_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../data.service */ "./src/app/data.service.ts");
/* harmony import */ var _models_receivedQuery__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../models/receivedQuery */ "./src/app/models/receivedQuery.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var QueryResultsComponent = /** @class */ (function () {
    function QueryResultsComponent(_dataservice) {
        this._dataservice = _dataservice;
        this.symptoms = Array.of('pain', 'death', 'suffering');
        this.bodyParts = Array.of('blood', 'lungs', 'mouth');
        this.paragraph1 = {
            DocumentId: 1,
            Content: 'I am paragraph. I want ro be fit  so that i dont cause this card to'
                + ' be very big. i want to hide my own contents so thatit doesnt'
                + ' make much troble he he he he i am fully visible now. This is a dummy paragraph'
                + ' to tell you that if you have cancer then there is no way saving you.'
        };
        this.paragraph2 = {
            DocumentId: 2,
            Content: 'This is to conform that your diaganosis confirms you have cancer. See your future in para 1.'
        };
        this.paragraphs = Array.of(this.paragraph1, this.paragraph2);
        this.cancer = 'Blood Cancer';
        this.dummy1 = new _models_receivedQuery__WEBPACK_IMPORTED_MODULE_2__["ReceivedQuery"](this.cancer, this.symptoms, this.bodyParts, this.paragraphs);
        this.queryResults = Array.of(this.dummy1);
    }
    QueryResultsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._dataservice.getQuery()
            .subscribe(
        // data => this.queryResults = data
        function (data) { return _this.queryRes = data; });
    };
    QueryResultsComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-query-results',
            template: __webpack_require__(/*! ./query-results.component.html */ "./src/app/query-results/query-results.component.html"),
            styles: [__webpack_require__(/*! ./query-results.component.css */ "./src/app/query-results/query-results.component.css")]
        }),
        __metadata("design:paramtypes", [_data_service__WEBPACK_IMPORTED_MODULE_1__["DataService"]])
    ], QueryResultsComponent);
    return QueryResultsComponent;
}());



/***/ }),

/***/ "./src/app/register/register.component.html":
/*!**************************************************!*\
  !*** ./src/app/register/register.component.html ***!
  \**************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"registration\">\n    <mat-card class=\"register-card\">\n\n        <mat-card-header>\n            <mat-card-title style=\"color=green\">Register</mat-card-title>\n        </mat-card-header>\n\n        <mat-card-content>\n            <form [formGroup]=\"registerForm\" (ngSubmit)=\"onSubmit(registerForm.value)\">\n            <table class=\"login-full-width\" cellspacing=\"0\">\n            <tr>\n                <td>\n                    <mat-form-field class=\"register-full-width\">\n                        <input matInput placeholder=\"First Name\" type=\"text\" formControlName=\"firstName\" class=\"form-control\" [ngClass]=\"{ 'is-invalid': submitted && f.firstName.errors }\" required />\n                        <div *ngIf=\"submitted && f.firstName.errors\" class=\"invalid-feedback\">\n                            <div *ngIf=\"f.firstName.errors.required\">First Name is required</div>\n                        </div>\n                    </mat-form-field>\n                </td>\n            </tr>\n\n            <tr>\n                <td>\n                    <mat-form-field class=\"register-full-width\">\n                        <input matInput placeholder=\"Last Name\" type=\"text\" formControlName=\"lastName\" class=\"form-control\" [ngClass]=\"{ 'is-invalid': submitted && f.lastName.errors }\" required />\n                        <div *ngIf=\"submitted && f.lastName.errors\" class=\"invalid-feedback\">\n                            <div *ngIf=\"f.lastName.errors.required\">Last Name is required</div>\n                        </div>\n                    </mat-form-field>\n                </td>\n            </tr>\n\n            <tr>\n                <td>\n                    <div class=\"form-group\">\n                        <label for=\"role\">Role</label>\n                        <input type=\"radio\"  value=\"General User\"  formControlName=\"role\" class=\"form-control\" [ngClass]=\"{ 'is-invalid': submitted && f.role.errors }\"> General User<br>\n                        <input type=\"radio\"  value=\"Subject Matter Expert\"  formControlName=\"role\" class=\"form-control\" [ngClass]=\"{ 'is-invalid': submitted && f.role.errors }\">  Subject matter expert <br>\n                    </div>\n                </td>\n            </tr>\n\n            <tr>\n                <td>\n                \n                    <mat-form-field class=\"register-full-width\">\n                        <input matInput placeholder=\"Username\" type=\"text\" formControlName=\"username\" class=\"form-control\" [ngClass]=\"{ 'is-invalid': submitted && f.username.errors }\" required/>\n                        <div *ngIf=\"submitted && f.username.errors\" class=\"invalid-feedback\">\n                            <div *ngIf=\"f.username.errors.required\">Username is required</div>\n                        </div>\n                    </mat-form-field>\n                </td>\n            <tr>\n\n            <tr>\n                <td>    \n                    <mat-form-field class=\"register-full-width\">\n                        <input matInput placeholder=\"Password\" type=\"password\" formControlName=\"password\" class=\"form-control\" [ngClass]=\"{ 'is-invalid': submitted && f.password.errors }\" required/>\n                        <div *ngIf=\"submitted && f.password.errors\" class=\"invalid-feedback\">\n                            <div *ngIf=\"f.password.errors.required\">Password is required</div>\n                            <div *ngIf=\"f.password.errors.minlength\">Password must be at least 6 characters</div>\n                        </div>\n                    </mat-form-field>\n                </td>\n            </tr>\n            \n            <tr>\n                <td>\n                    <mat-card-actions>\n                        <div class=\"form-group\">\n                            <button mat-raised-button>Register</button>\n                            <img *ngIf=\"loading\" src=\"data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==\" />\n                        </div>\n                    </mat-card-actions>\n                </td>\n            </tr></table>\n            </form>\n        </mat-card-content>\n    </mat-card>\n</div>"

/***/ }),

/***/ "./src/app/register/register.component.ts":
/*!************************************************!*\
  !*** ./src/app/register/register.component.ts ***!
  \************************************************/
/*! exports provided: RegisterComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RegisterComponent", function() { return RegisterComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _services__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../_services */ "./src/app/_services/index.ts");
/* harmony import */ var angular_alert_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! angular-alert-module */ "./node_modules/angular-alert-module/fesm5/alerts.js");
/* harmony import */ var _registration_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../registration.service */ "./src/app/registration.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var RegisterComponent = /** @class */ (function () {
    function RegisterComponent(formBuilder, router, register, userService, alertService, alerts) {
        this.formBuilder = formBuilder;
        this.router = router;
        this.register = register;
        this.userService = userService;
        this.alertService = alertService;
        this.alerts = alerts;
        this.loading = false;
        this.submitted = false;
        this.details = [];
    }
    RegisterComponent.prototype.ngOnInit = function () {
        this.registerForm = this.formBuilder.group({
            firstName: ['', _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required],
            lastName: ['', _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required],
            username: ['', _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required],
            password: ['', [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].minLength(6)]],
            role: ['', _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required]
        });
    };
    Object.defineProperty(RegisterComponent.prototype, "f", {
        // convenience getter for easy access to form fields
        get: function () { return this.registerForm.controls; },
        enumerable: true,
        configurable: true
    });
    RegisterComponent.prototype.onSubmit = function (value) {
        var _this = this;
        this.submitted = true;
        // stop here if form is invalid
        if (this.registerForm.invalid) {
            return;
        }
        this.details.push(value);
        console.log(this.details);
        this.loading = true;
        var fn = this.registerForm.controls['firstName'].value;
        var ln = this.registerForm.controls['lastName'].value;
        var un = this.registerForm.controls['username'].value;
        var r = this.registerForm.controls['role'].value;
        var pwd = this.registerForm.controls['password'].value;
        this.register.registerUser(fn, ln, un, r, pwd)
            .subscribe(function (data) {
            _this.alerts.setMessage('succesfully saved', 'success');
            _this.router.navigate(['/login']);
        });
    };
    RegisterComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            template: __webpack_require__(/*! ./register.component.html */ "./src/app/register/register.component.html"),
            providers: [_services__WEBPACK_IMPORTED_MODULE_3__["AlertService"], angular_alert_module__WEBPACK_IMPORTED_MODULE_4__["AlertsService"], _services__WEBPACK_IMPORTED_MODULE_3__["UserService"]]
        }),
        __metadata("design:paramtypes", [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormBuilder"],
            _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _registration_service__WEBPACK_IMPORTED_MODULE_5__["RegistrationService"],
            _services__WEBPACK_IMPORTED_MODULE_3__["UserService"],
            _services__WEBPACK_IMPORTED_MODULE_3__["AlertService"],
            angular_alert_module__WEBPACK_IMPORTED_MODULE_4__["AlertsService"]])
    ], RegisterComponent);
    return RegisterComponent;
}());



/***/ }),

/***/ "./src/app/registration.service.ts":
/*!*****************************************!*\
  !*** ./src/app/registration.service.ts ***!
  \*****************************************/
/*! exports provided: RegistrationService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RegistrationService", function() { return RegistrationService; });
/* harmony import */ var _models_reg_userdetails__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./models/reg/userdetails */ "./src/app/models/reg/userdetails.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var httpOptions = {
    headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({
        'Content-Type': 'application/json',
    })
};
var RegistrationService = /** @class */ (function () {
    function RegistrationService(http) {
        this.http = http;
        this.registerUrl = 'http://localhost:8181/api/v1/saveuser';
    }
    RegistrationService.prototype.registerUser = function (fn, ln, username, role, pass) {
        var details = new _models_reg_userdetails__WEBPACK_IMPORTED_MODULE_0__["UserDetails"](fn, ln, username, role, pass);
        return this.http.post(this.registerUrl, details, httpOptions);
    };
    RegistrationService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], RegistrationService);
    return RegistrationService;
}());



/***/ }),

/***/ "./src/app/share.service.ts":
/*!**********************************!*\
  !*** ./src/app/share.service.ts ***!
  \**********************************/
/*! exports provided: ShareService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ShareService", function() { return ShareService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var rxjs_internal_BehaviorSubject__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs/internal/BehaviorSubject */ "./node_modules/rxjs/internal/BehaviorSubject.js");
/* harmony import */ var rxjs_internal_BehaviorSubject__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(rxjs_internal_BehaviorSubject__WEBPACK_IMPORTED_MODULE_1__);
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};


var ShareService = /** @class */ (function () {
    function ShareService() {
        this.valueObs = new rxjs_internal_BehaviorSubject__WEBPACK_IMPORTED_MODULE_1__["BehaviorSubject"](null);
    }
    ShareService.prototype.setValue = function (value) {
        this.valueObs.next(value);
        console.log('value after setValue() is called: ' + this.valueObs.value);
    };
    ShareService.prototype.getValue = function () {
        return this.valueObs;
    };
    ShareService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])()
    ], ShareService);
    return ShareService;
}());



/***/ }),

/***/ "./src/app/user-query.service.ts":
/*!***************************************!*\
  !*** ./src/app/user-query.service.ts ***!
  \***************************************/
/*! exports provided: UserQueryService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserQueryService", function() { return UserQueryService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var httpOptions = {
    headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({
        'Content-Type': 'application/json',
    })
};
var UserQueryService = /** @class */ (function () {
    function UserQueryService(http) {
        this.http = http;
        // nlp-pipeline service url-path
        this.getRequest = 'http://localhost:8148/kv/';
    }
    UserQueryService.prototype.getDisease = function (medicalConditionId) {
        return this.http.get(this.getRequest + medicalConditionId);
    };
    UserQueryService.prototype.postUserQuery = function (inputText) {
        var getUrl = this.getRequest + inputText;
        this.http.get(getUrl, httpOptions)
            .subscribe(function (data) {
            if (data == null) {
                console.log(data);
            }
        });
    };
    UserQueryService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], UserQueryService);
    return UserQueryService;
}());



/***/ }),

/***/ "./src/app/user-query/user-query.component.css":
/*!*****************************************************!*\
  !*** ./src/app/user-query/user-query.component.css ***!
  \*****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = " .userInput-card {\n  height: 100%;\n  color: white;\n  text-align: center;\n  box-shadow: none;\n}\n\nbutton {\n  background-color: #5cb85c;\n  color: white;\n}\n\n.userInput-full-width {\n  margin: auto;\n  width: 85%;\n  color: black;\n}\n\nmat-card-header, mat-card-title {\n  color: black;\n  font-family: source sans pro, sans-serif;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvdXNlci1xdWVyeS91c2VyLXF1ZXJ5LmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkNBQUM7RUFDQyxhQUFhO0VBQ2IsYUFBYTtFQUNiLG1CQUFtQjtFQUNuQixpQkFBaUI7Q0FDbEI7O0FBRUQ7RUFDRSwwQkFBMEI7RUFDMUIsYUFBYTtDQUNkOztBQUVEO0VBQ0UsYUFBYTtFQUNiLFdBQVc7RUFDWCxhQUFhO0NBQ2Q7O0FBRUQ7RUFDRSxhQUFhO0VBQ2IseUNBQXlDO0NBQzFDIiwiZmlsZSI6InNyYy9hcHAvdXNlci1xdWVyeS91c2VyLXF1ZXJ5LmNvbXBvbmVudC5jc3MiLCJzb3VyY2VzQ29udGVudCI6WyIgLnVzZXJJbnB1dC1jYXJkIHtcbiAgaGVpZ2h0OiAxMDAlO1xuICBjb2xvcjogd2hpdGU7XG4gIHRleHQtYWxpZ246IGNlbnRlcjtcbiAgYm94LXNoYWRvdzogbm9uZTtcbn1cblxuYnV0dG9uIHtcbiAgYmFja2dyb3VuZC1jb2xvcjogIzVjYjg1YztcbiAgY29sb3I6IHdoaXRlO1xufVxuXG4udXNlcklucHV0LWZ1bGwtd2lkdGgge1xuICBtYXJnaW46IGF1dG87XG4gIHdpZHRoOiA4NSU7XG4gIGNvbG9yOiBibGFjaztcbn1cblxubWF0LWNhcmQtaGVhZGVyLCBtYXQtY2FyZC10aXRsZSB7XG4gIGNvbG9yOiBibGFjaztcbiAgZm9udC1mYW1pbHk6IHNvdXJjZSBzYW5zIHBybywgc2Fucy1zZXJpZjtcbn0iXX0= */"

/***/ }),

/***/ "./src/app/user-query/user-query.component.html":
/*!******************************************************!*\
  !*** ./src/app/user-query/user-query.component.html ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<head>\n  <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'>\n</head>\n<mat-card class=\"userInput-card\" layout-align=\"center none\">\n   <mat-card-header>\n     <div style=\"margin:auto\">\n      <mat-card-title>Experiencing symptoms but not sure what they mean? Use Knowledge Vault to help determine possible causes and when to see a doctor.</mat-card-title>\n      </div>\n   </mat-card-header>\n   <mat-card-content>\n     <form class=\"userInput-form\">\n       <table class=\"userInput-full-width\" cellspacing=\"0\">\n         <tr>\n           <td>\n             <mat-form-field class=\"userInput-full-width\">\n             <input matInput placeholder=\"Enter your Query\" type=\"search\" [(ngModel)]=\"msg\" color=\"primary\" name=\"search\" (keyup.enter)=\"search($event)\" required>\n             </mat-form-field>\n           </td>\n         </tr>\n        </table>\n     </form>\n     <mat-spinner [style.display]=\"showSpinner ? 'block' : 'none'\"></mat-spinner>\n   </mat-card-content>\n   <mat-card-actions>\n   <button mat-raised-button (click)=\"toggleVoiceRecognition()\">{{ started ? 'Stop voice to text' : 'Start voice to text' }}</button>\n     <button mat-raised-button (click)=\"search()\">Search</button>\n   </mat-card-actions>\n\n "

/***/ }),

/***/ "./src/app/user-query/user-query.component.ts":
/*!****************************************************!*\
  !*** ./src/app/user-query/user-query.component.ts ***!
  \****************************************************/
/*! exports provided: UserQueryComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserQueryComponent", function() { return UserQueryComponent; });
/* harmony import */ var _lib_speech_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./../../lib/speech.service */ "./src/lib/speech.service.ts");
/* harmony import */ var _user_query_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./../user-query.service */ "./src/app/user-query.service.ts");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm5/operators/index.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var UserQueryComponent = /** @class */ (function () {
    function UserQueryComponent(router, service, speech) {
        this.router = router;
        this.service = service;
        this.speech = speech;
        this.msg = '';
        this.context = '';
        this.subscription = rxjs__WEBPACK_IMPORTED_MODULE_3__["Subscription"].EMPTY;
        this.started = false;
        this._destroyed = new rxjs__WEBPACK_IMPORTED_MODULE_3__["Subject"]();
    }
    UserQueryComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.speech.message.pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["takeUntil"])(this._destroyed)).subscribe(function (msg) { return _this.msg = msg.message; });
        this.speech.context.pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["takeUntil"])(this._destroyed)).subscribe(function (context) { return _this.context = context; });
        this.good = { message: 'Try me!' };
        this.speech.started.pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["takeUntil"])(this._destroyed)).subscribe(function (started) { return _this.started = started; });
    };
    UserQueryComponent.prototype.ngOnDestroy = function () {
        this._destroyed.next();
        this._destroyed.complete();
        this.subscription.unsubscribe();
    };
    UserQueryComponent.prototype.search = function () {
        console.log(this.msg);
        this.service.postUserQuery(this.msg);
        this.speech.stop();
        this.router.navigate(['queryresults']);
    };
    UserQueryComponent.prototype.toggleVoiceRecognition = function () {
        if (this.started) {
            this.speech.stop();
        }
        else {
            this.speech.start();
        }
    };
    UserQueryComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["Component"])({
            selector: 'app-user-query',
            template: __webpack_require__(/*! ./user-query.component.html */ "./src/app/user-query/user-query.component.html"),
            styles: [__webpack_require__(/*! ./user-query.component.css */ "./src/app/user-query/user-query.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_5__["Router"], _user_query_service__WEBPACK_IMPORTED_MODULE_1__["UserQueryService"], _lib_speech_service__WEBPACK_IMPORTED_MODULE_0__["SpeechService"]])
    ], UserQueryComponent);
    return UserQueryComponent;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false,
    apiUrl: 'http://localhost:8184',
    apiUrl2: 'http://localhost:8182',
    apiUrl3: 'http://localhost:8080',
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/lib/index.ts":
/*!**************************!*\
  !*** ./src/lib/index.ts ***!
  \**************************/
/*! exports provided: SpeechModule, SpeechService, SpeechActionDirective, SpeechContextDirective */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _public_api__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./public_api */ "./src/lib/public_api.ts");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "SpeechModule", function() { return _public_api__WEBPACK_IMPORTED_MODULE_0__["SpeechModule"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "SpeechService", function() { return _public_api__WEBPACK_IMPORTED_MODULE_0__["SpeechService"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "SpeechActionDirective", function() { return _public_api__WEBPACK_IMPORTED_MODULE_0__["SpeechActionDirective"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "SpeechContextDirective", function() { return _public_api__WEBPACK_IMPORTED_MODULE_0__["SpeechContextDirective"]; });




/***/ }),

/***/ "./src/lib/module.ts":
/*!***************************!*\
  !*** ./src/lib/module.ts ***!
  \***************************/
/*! exports provided: SpeechModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SpeechModule", function() { return SpeechModule; });
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _speech_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./speech.service */ "./src/lib/speech.service.ts");
/* harmony import */ var _speech_action_directive__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./speech-action.directive */ "./src/lib/speech-action.directive.ts");
/* harmony import */ var _speech_context_directive__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./speech-context.directive */ "./src/lib/speech-context.directive.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};





var SpeechModule = /** @class */ (function () {
    function SpeechModule() {
    }
    SpeechModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [
                _speech_action_directive__WEBPACK_IMPORTED_MODULE_3__["SpeechActionDirective"],
                _speech_context_directive__WEBPACK_IMPORTED_MODULE_4__["SpeechContextDirective"]
            ],
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"]
            ],
            providers: [_speech_service__WEBPACK_IMPORTED_MODULE_2__["SpeechService"]],
            exports: [
                _speech_action_directive__WEBPACK_IMPORTED_MODULE_3__["SpeechActionDirective"],
                _speech_context_directive__WEBPACK_IMPORTED_MODULE_4__["SpeechContextDirective"]
            ]
        })
    ], SpeechModule);
    return SpeechModule;
}());



/***/ }),

/***/ "./src/lib/public_api.ts":
/*!*******************************!*\
  !*** ./src/lib/public_api.ts ***!
  \*******************************/
/*! exports provided: SpeechModule, SpeechService, SpeechActionDirective, SpeechContextDirective */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _module__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./module */ "./src/lib/module.ts");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "SpeechModule", function() { return _module__WEBPACK_IMPORTED_MODULE_0__["SpeechModule"]; });

/* harmony import */ var _speech_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./speech.service */ "./src/lib/speech.service.ts");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "SpeechService", function() { return _speech_service__WEBPACK_IMPORTED_MODULE_1__["SpeechService"]; });

/* harmony import */ var _speech_action_directive__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./speech-action.directive */ "./src/lib/speech-action.directive.ts");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "SpeechActionDirective", function() { return _speech_action_directive__WEBPACK_IMPORTED_MODULE_2__["SpeechActionDirective"]; });

/* harmony import */ var _speech_context_directive__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./speech-context.directive */ "./src/lib/speech-context.directive.ts");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "SpeechContextDirective", function() { return _speech_context_directive__WEBPACK_IMPORTED_MODULE_3__["SpeechContextDirective"]; });







/***/ }),

/***/ "./src/lib/speech-action.directive.ts":
/*!********************************************!*\
  !*** ./src/lib/speech-action.directive.ts ***!
  \********************************************/
/*! exports provided: SpeechActionDirective */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SpeechActionDirective", function() { return SpeechActionDirective; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm5/operators/index.js");
/* harmony import */ var _speech_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./speech.service */ "./src/lib/speech.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var SpeechActionDirective = /** @class */ (function () {
    function SpeechActionDirective(_view, speech) {
        this._view = _view;
        this.speech = speech;
        this.ngSpeechActionContext = [];
        this._destroyed = new rxjs__WEBPACK_IMPORTED_MODULE_1__["Subject"]();
    }
    SpeechActionDirective.prototype.ngOnInit = function () {
        var _this = this;
        this.speech.declareCommand(this.ngSpeechActionCommand, this.ngSpeechActionContext);
        this.speech.command.pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_2__["takeUntil"])(this._destroyed), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_2__["filter"])(function (command) { return _this.match(command); })).subscribe(function (_command) {
            var component = _this._view.injector.view.component;
            _this.ngSpeechAction.bind(component)();
        });
    };
    SpeechActionDirective.prototype.ngOnDestroy = function () {
        this._destroyed.next();
        this._destroyed.complete();
    };
    SpeechActionDirective.prototype.match = function (command) {
        var context = this.ngSpeechActionContext.map(function (w) { return w.toLowerCase(); }).join('/');
        return command.context === context && command.command === this.ngSpeechActionCommand.toLowerCase();
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Object)
    ], SpeechActionDirective.prototype, "ngSpeechAction", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", String)
    ], SpeechActionDirective.prototype, "ngSpeechActionCommand", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Array)
    ], SpeechActionDirective.prototype, "ngSpeechActionContext", void 0);
    SpeechActionDirective = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Directive"])({
            selector: '[ngSpeechAction]'
        }),
        __metadata("design:paramtypes", [_angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewContainerRef"],
            _speech_service__WEBPACK_IMPORTED_MODULE_3__["SpeechService"]])
    ], SpeechActionDirective);
    return SpeechActionDirective;
}());



/***/ }),

/***/ "./src/lib/speech-context.directive.ts":
/*!*********************************************!*\
  !*** ./src/lib/speech-context.directive.ts ***!
  \*********************************************/
/*! exports provided: SpeechContextDirective */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SpeechContextDirective", function() { return SpeechContextDirective; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm5/operators/index.js");
/* harmony import */ var _speech_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./speech.service */ "./src/lib/speech.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var SpeechContextDirective = /** @class */ (function () {
    function SpeechContextDirective(speech) {
        this.speech = speech;
        this._destroyed = new rxjs__WEBPACK_IMPORTED_MODULE_1__["Subject"]();
    }
    SpeechContextDirective.prototype.ngOnInit = function () {
        var _this = this;
        this.speech.declareContext(this.ngSpeechContext);
        var localContext = this.ngSpeechContext.map(function (w) { return w.toLowerCase(); });
        this.speech.context.pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_2__["takeUntil"])(this._destroyed)).subscribe(function (context) {
            if (context === localContext.join('/')) {
                _this.speechClass = 'speech-active-context';
            }
            else if (context === localContext.slice(0, -1).join('/')) {
                _this.speechClass = 'speech-active-context-child';
            }
            else if (context.startsWith(localContext.join('/'))) {
                _this.speechClass = 'speech-active-context-ancestor';
            }
            else {
                _this.speechClass = '';
            }
        });
    };
    SpeechContextDirective.prototype.ngOnDestroy = function () {
        this._destroyed.next();
        this._destroyed.complete();
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Array)
    ], SpeechContextDirective.prototype, "ngSpeechContext", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["HostBinding"])('class'),
        __metadata("design:type", String)
    ], SpeechContextDirective.prototype, "speechClass", void 0);
    SpeechContextDirective = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Directive"])({
            selector: '[ngSpeechContext]'
        }),
        __metadata("design:paramtypes", [_speech_service__WEBPACK_IMPORTED_MODULE_3__["SpeechService"]])
    ], SpeechContextDirective);
    return SpeechContextDirective;
}());



/***/ }),

/***/ "./src/lib/speech.service.ts":
/*!***********************************!*\
  !*** ./src/lib/speech.service.ts ***!
  \***********************************/
/*! exports provided: SpeechService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SpeechService", function() { return SpeechService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm5/index.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm5/operators/index.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (undefined && undefined.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};



var DEFAULT_GRAMMAR = "#JSGF V1.0; grammar Digits;\npublic <Digits> = ( <digit> ) + ;\n<digit> = ( zero | one | two | three | four | five | six | seven | eight | nine );";
var SpeechService = /** @class */ (function () {
    function SpeechService(zone, lang) {
        var _this = this;
        this.zone = zone;
        this.lang = lang;
        this.message = new rxjs__WEBPACK_IMPORTED_MODULE_1__["Subject"]();
        this.command = new rxjs__WEBPACK_IMPORTED_MODULE_1__["Subject"]();
        this.commands = {};
        this.context = new rxjs__WEBPACK_IMPORTED_MODULE_1__["BehaviorSubject"]('');
        this.refreshGrammar = new rxjs__WEBPACK_IMPORTED_MODULE_1__["BehaviorSubject"](false);
        this.started = new rxjs__WEBPACK_IMPORTED_MODULE_1__["BehaviorSubject"](false);
        this._destroyed = new rxjs__WEBPACK_IMPORTED_MODULE_1__["Subject"]();
        var SpeechRecognition = window['SpeechRecognition'] || window['webkitSpeechRecognition'];
        this.recognition = new SpeechRecognition();
        this.recognition.lang = lang;
        this.recognition.interimResults = false;
        this.recognition.maxAlternatives = 1;
        this.recognition.continuous = true;
        this.recognition.onresult = function (event) {
            var message = {};
            var word = '';
            if (event.results) {
                var result = event.results[event.resultIndex];
                if (result.isFinal) {
                    if (result[0].confidence < 0.3) {
                        message = { error: true, message: 'Cannot recognize' };
                    }
                    else {
                        word = result[0].transcript.trim().toLowerCase();
                        message = { success: true, message: word };
                    }
                }
            }
            _this.zone.run(function () {
                if (message['error']) {
                    _this.message.error(message);
                }
                else {
                    _this.message.next(message);
                    var context = _this.getContextForWord(word);
                    if (context) {
                        _this.context.next(context);
                    }
                    else {
                        var isCommand = _this.commands[_this.context.value] && _this.commands[_this.context.value][word];
                        if (isCommand) {
                            _this.command.next({ context: _this.context.value, command: word });
                        }
                        else {
                            // try to match a global context command
                            var isGlobalCommand = _this.commands[''] && _this.commands[''][word];
                            if (isGlobalCommand) {
                                _this.command.next({ context: '', command: word });
                            }
                        }
                    }
                }
            });
        };
        this.recognition.onerror = function (error) {
            _this.zone.run(function () {
                _this.message.error(error);
            });
        };
        this.recognition.onstart = function () {
            _this.zone.run(function () {
                _this.started.next(true);
            });
        };
        this.recognition.onend = function () {
            _this.zone.run(function () {
                _this.started.next(false);
            });
        };
        this.refreshGrammar.pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_2__["takeUntil"])(this._destroyed), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_2__["debounceTime"])(500)).subscribe(function () {
            _this.setGrammar();
        });
    }
    SpeechService.prototype.ngOnDestroy = function () {
        this._destroyed.next();
        this._destroyed.complete();
    };
    SpeechService.prototype.start = function () {
        this.recognition.start();
    };
    SpeechService.prototype.stop = function () {
        this.recognition.stop();
    };
    SpeechService.prototype.declareContext = function (context) {
        var contextKey = context.map(function (w) { return w.toLowerCase(); }).join('/');
        if (!this.commands[contextKey]) {
            this.commands[contextKey] = {};
        }
        this.refreshGrammar.next(true);
    };
    SpeechService.prototype.declareCommand = function (command, context) {
        var contextKey = context.map(function (w) { return w.toLowerCase(); }).join('/');
        if (!this.commands[contextKey]) {
            this.commands[contextKey] = {};
        }
        this.commands[contextKey][command.toLowerCase()] = true;
        this.refreshGrammar.next(true);
    };
    SpeechService.prototype.setContext = function (context) {
        var contextKey = context.map(function (w) { return w.toLowerCase(); }).join('/');
        this.context.next(contextKey);
    };
    SpeechService.prototype.getContextForWord = function (word) {
        // first try to match a subcontext of the current context
        var context = this.context.value ? this.context.value + '/' + word : word;
        if (this.commands[context]) {
            return context;
        }
        // then try top-level context
        if (this.commands[word]) {
            return word;
        }
        return null;
    };
    SpeechService.prototype.setGrammar = function () {
        var _this = this;
        var SpeechGrammarList = window['SpeechGrammarList'] || window['webkitSpeechGrammarList'];
        var words = {};
        Object.keys(this.commands).forEach(function (context) {
            context.split('/').forEach(function (word) {
                words[word] = true;
            });
            Object.keys(_this.commands[context]).forEach(function (command) { return words[command] = true; });
        });
        var grammar = DEFAULT_GRAMMAR + ' public <command> = ' + Object.keys(words).join(' | ') + ' ;';
        var speechRecognitionList = new SpeechGrammarList();
        speechRecognitionList.addFromString(grammar, 1);
        this.recognition.grammars = speechRecognitionList;
    };
    SpeechService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __param(1, Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Inject"])('SPEECH_LANG')),
        __metadata("design:paramtypes", [_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgZone"], String])
    ], SpeechService);
    return SpeechService;
}());



/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.error(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! /home/cgi/Documents/knowledge-vault/knowledge-vault-ui/src/main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map