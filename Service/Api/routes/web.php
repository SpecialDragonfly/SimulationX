<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$app->post('/exchange', 'ResourceController@exchangeResource');

$app->get('/resources', 'ResourceController@getResources');

$app->get('/ping', function() {
    \Illuminate\Support\Facades\Log::info("ping");
    return new \Illuminate\Http\Response("ok");
});
