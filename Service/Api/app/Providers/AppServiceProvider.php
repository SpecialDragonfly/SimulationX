<?php

namespace App\Providers;

use App\Model\Resource;
use App\Model\Service;
use Illuminate\Support\ServiceProvider;
use Ramsey\Uuid\Uuid;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     *
     * @return void
     */
    public function register()
    {
        $this->app->singleton("service", function() {
            $service = json_decode(file_get_contents(resource_path(".sxpayload")), true);

            $src = new Resource(
                $service["src"]["name"],
                $service["src"]["payload"]
            );

            $exchange = new Resource(
                $service["exchange"]["name"],
                $service["exchange"]["payload"]
            );

            return new Service(
                Uuid::uuid4()->toString(),
                $src,
                $exchange,
                $service["factor"]
            );
        });
    }
}
