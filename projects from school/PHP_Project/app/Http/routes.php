<?php

Route::get('/', 'HomeController@index');
Route::get('home', 'HomeController@index');

//Admin panel routes
Route::resource('content_area', 'ContentAreaController');
Route::resource('css_template', 'CssTemplateController');
Route::resource('pages', 'PagesController');
Route::resource('admin', 'AdminController');
Route::resource('users', 'UserController');
Route::resource('articles', 'ArticlesController');
Route::get('site/{page}/{article}', 'SiteController@page');
Route::resource('site', 'SiteController');

Route::controllers([
    'auth' => 'Auth\AuthController',
    'password' => 'Auth\PasswordController',
]);
