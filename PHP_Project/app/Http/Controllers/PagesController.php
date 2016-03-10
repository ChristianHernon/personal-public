<?php namespace App\Http\Controllers;

use App\Http\Requests;
use App\Http\Requests\CreatePageRequest;
use App\Page;
use App\User;
use Auth;

/**
 * Class PagesController
 * @package App\Http\Controllers
 */
class PagesController extends Controller
{

    /**
     *
     */
    public function __construct()
    {
        $this->middleware('admin');
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\View\View
     */
    public function index()
    {
        $pages = Page::all();

        return view('pages.index', compact('pages'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        return view('pages.create');
    }


    /**
     * Store a newly created resource in storage.
     *
     * @param CreatePageRequest $request
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function store(CreatePageRequest $request)
    {
        $request['modified_by'] = Auth::id();
        Auth::user()->pages()->save(new Page($request->all()));

        return redirect('admin/pages');
    }


    /**
     * Display the specified resource.
     *
     * @param $id
     * @return \Illuminate\View\View
     */
    public function show($id)
    {
        $page = Page::findOrFail($id);

        $result = User::where('id', $page->created_by)->get(['first_name']);
        $page->created_by = $result[0]->first_name;

        $result = User::where('id', $page->modified_by)->get(['first_name']);
        $page->modified_by = $result[0]->first_name;

        return view('pages.show', compact('page'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\View\View
     */
    public function edit($id)
    {
        $page = Page::findOrFail($id);

        return view('pages.edit', compact('page'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  int $id
     * @param CreatePageRequest $request
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function update($id, CreatePageRequest $request)
    {
        $request['modified_by'] = Auth::id();
        $page = Page::findOrFail($id);
        $page->update($request->all());

        return redirect('pages');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function destroy($id)
    {
        $page = Page::findOrFail($id);
        $page->delete();

        return redirect('pages');
    }

}
