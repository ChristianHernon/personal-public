<?php namespace App\Http\Controllers;

use App\CSS_Template;
use App\Http\Requests;
use App\Http\Requests\CreateCssTemplateRequest;
use App\User;
use Auth;
use Request;

/**
 * Class CssTemplateController
 * @package App\Http\Controllers
 */
class CssTemplateController extends Controller
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
        $css_templates = CSS_Template::all();

        return view('css_template.index', compact('css_templates'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        return view('css_template.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param CreateCssTemplateRequest $request
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function store(CreateCssTemplateRequest $request)
    {
        $active = Request::input('active');
        $this->checkActive($active);
        $request['modified_by'] = Auth::id();
        Auth::user()->templates()->save(new CSS_Template($request->all()));

        return redirect('css_template');
    }

    /**
     * Display the specified resource.
     *
     * @param $id
     * @return \Illuminate\View\View
     */
    public function show($id)
    {
        $css = CSS_Template::findOrFail($id);

        $result = User::where('id', $css->created_by)->get(['first_name']);
        $css->created_by = $result[0]->first_name;

        $result = User::where('id', $css->modified_by)->get(['first_name']);
        $css->modified_by = $result[0]->first_name;

        return view('css_template.show', compact('css'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param $id
     * @return \Illuminate\View\View
     */
    public function edit($id)
    {
        $css = CSS_Template::findOrFail($id);

        return view('css_template.edit', compact('css'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param $id
     * @param CreateCssTemplateRequest $request
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function update($id, CreateCssTemplateRequest $request)
    {
        $request['modified_by'] = Auth::id();
        $active = Request::input('active');
        $this->checkActive($active);
        $css = CSS_Template::findOrFail($id);
        $css->update($request->all());

        return redirect('css_template');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param $id
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function destroy($id)
    {
        $css = CSS_Template::findOrFail($id);
        $css->delete();

        return redirect('css_template');
    }

    /**
     * Set all CSS styles to inactive if the style in the request is to be active.
     *
     * @param $active
     */
    public function checkActive($active)
    {
        if ($active == "1") {
            $templates = CSS_Template::all();
            foreach ($templates as $template) {
                $template->update(['active' => null]);
            }
        }
    }

}
