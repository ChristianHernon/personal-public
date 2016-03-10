<?php namespace App\Http\Middleware;

use Auth;
use Closure;
use Redirect;
use URL;

class AdminMiddleware
{

    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  \Closure $next
     * @return mixed
     */
    public function handle($request, Closure $next)
    {
        if (!Auth::guest()) {

            $user = $request->user();
            $uri = $request->path();
            if ($uri == 'users' && $user->isAdmin()) {
                return $next($request);
            } elseif ($uri == 'admin' && ($user->isAdmin() || $user->isEditor())) {
                return $next($request);
            } elseif ($uri != 'articles' && $uri != 'users' && $user->isEditor()) {
                return $next($request);
            } elseif (0 === strpos($uri, 'article') && ($user->isAuthor() || $user->isEditor())) {
                return $next($request);
            } else {
                return Redirect::to(URL::previous());
            }
        }

        return redirect('auth/login');
    }

}
