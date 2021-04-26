<?php namespace App\Http\Controllers;

use App\Content_Area;
use App\User;
use App\Http\Requests;
use App\Http\Requests\CreateContentAreaRequest;
use Auth;

/**
 * Class ContentAreaController
 * @package App\Http\Controllers
 */
class ContentAreaController extends Controller
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
        $contentAreas = Content_Area::all();

        return view('content_areas.index', compact('contentAreas'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        return view('content_areas.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param CreateContentAreaRequest $request
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function store(CreateContentAreaRequest $request)
    {
        $request['modified_by'] = Auth::id();
        Auth::user()->areas()->save(new Content_Area($request->all()));

        return redirect('admin/content_areas');
    }

    /**
     * Display the specified resource.
     *
     * @param $id
     * @return \Illuminate\View\View
     */
    public function show($id)
    {
        $content_Area = Content_Area::findOrFail($id);

        $result = User::where('id', $content_Area->created_by)->get(['first_name']);
        $content_Area->created_by = $result[0]->first_name;

        $result = User::where('id', $content_Area->modified_by)->get(['first_name']);
        $content_Area->modified_by = $result[0]->first_name;

        return view('content_areas.show', compact('content_Area'));
    }

    /**
     * Display the form for editing the specified resource.
     *
     * @param $id
     * @return \Illuminate\View\View
     */
    public function edit($id)
    {
        $content_Area = Content_Area::findOrFail($id);

        return view('content_areas.edit', compact('content_Area'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param $id
     * @param CreateContentAreaRequest $request
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function update($id, CreateContentAreaRequest $request)
    {
        $request['modified_by'] = Auth::id();
        $content_Area = Content_Area::findOrFail($id);
        $content_Area->update($request->all());

        return redirect('content_area');
    }

    /**
     * Delete the specified resource from storage.
     *
     * @param $id
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function destroy($id)
    {
        $content_Area = Content_Area::findOrFail($id);
        $content_Area->delete();

        return redirect('content_area');
    }

}
