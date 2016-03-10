<?php namespace App\Http\Controllers;

use App\Http\Requests;
use Redirect;

/**
 * Class AdminController
 * @package App\Http\Controllers
 */
class AdminController extends Controller
{
    /**
     * Check to see if there is a valid user logged in.
     *
     *
     */
    public function __construct()
    {
        $this->middleware('admin');
    }

    /**
     * Display the listing of the resources.
     *
     * @return \Illuminate\View\View
     */
    public function index()
    {
        return view('admin');
    }

    /**
     * Display the specified resource.
     *
     * @param $id
     * @return \Illuminate\Http\RedirectResponse
     */
    public function show($id)
    {
        if ($id == 'articles') {
            return Redirect::action('ArticlesController@index');
        } else if ($id == 'pages') {
            return Redirect::action('PagesController@index');
        } else if ($id == 'content_areas') {
            return Redirect::action('ContentAreaController@index');
        } else if ($id == 'css_templates') {
            return Redirect::action('CssTemplateController@index');
        } else if ($id == 'users') {
            return Redirect::action('UserController@index');
        } else {
            return Redirect::action('AdminController@index');
        }
    }

}
