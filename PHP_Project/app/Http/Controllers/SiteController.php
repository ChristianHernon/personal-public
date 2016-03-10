<?php namespace App\Http\Controllers;

use App\Article;
use App\Content_Area;
use App\Http\Requests;
use App\Http\Requests\CreateSiteRequest;
use App\Page;

/**
 * Class SiteController
 * @package App\Http\Controllers
 */
class SiteController extends Controller
{

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\View\View
     */
    public function index()
    {
        $pages = Page::all();

        return view('site', compact('pages'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param Request $request
     * @return \Illuminate\View\View
     */
    public function store(CreateSiteRequest $request)
    {
        $pages = Page::all();
        $query = '%' . $request->input('search') . '%';
        $pageContent = Article::where('title', 'like', $query)->orWhere('body', 'like', $query)->get();

        return view('site.search', compact('pageContent', 'pages'));
    }

    /**
     * Display the specified resource.
     *
     * @param  string $alias
     * @return \Illuminate\View\View
     */
    public function show($alias)
    {
        $pages = Page::all();
        $page = Page::where('alias', $alias)->get()->first();
        $contentAreas = Content_Area::orderBy('orderBy', 'ASC')->get();
        $pageContent = Article::where('page', 0)->orWhere('page', $page->id)
            ->orderBy('area', 'ASC')
            ->orderBy('page', 'ASC')->get();

        return view('site.index', compact('pageContent', 'pages', 'contentAreas', 'page'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  int $id
     * @return Response
     */
    public function update($id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return Response
     */
    public function destroy($id)
    {
        //
    }

    /**
     *
     *
     * @param string $page
     * @param string $article
     */
    public function page($page, $id)
    {
        echo 'got here';
    }

}
