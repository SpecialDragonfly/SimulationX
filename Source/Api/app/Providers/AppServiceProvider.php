<?php

namespace App\Providers;

use App\Model\Resource;
use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     *
     * @return void
     */
    public function register()
    {
        $this->app->singleton("resource", function() {
            $resourceObject = json_decode(file_get_contents(resource_path(".sxpayload")));

            return new Resource(
               $resourceObject->name,
               $resourceObject->payload
            );
        });
    }
}
