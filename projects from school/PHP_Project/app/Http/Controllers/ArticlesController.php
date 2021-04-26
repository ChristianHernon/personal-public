<?php namespace App\Http\Controllers;

use App\Article;
use App\Content_Area;
use App\Http\Requests;
use App\Http\Requests\CreateArticleRequest;
use App\Page;
use App\User;
use Auth;


/**
 * Class ArticlesController
 * @package App\Http\Controllers
 */
class ArticlesController extends Controller
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
        $articles = Article::all();

        return view('articles.index', compact('articles'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        $pages = ['0' => 'All Pages'] + Page::oldest()->lists('name', 'id');
        $content_area = Content_Area::oldest()->lists('name', 'id');

        return view('articles.create', compact('pages', 'content_area'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  CreateArticleRequest $request
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function store(CreateArticleRequest $request)
    {
        $request['modified_by'] = Auth::id();
        Auth::user()->articles()->save(new Article($request->all()));

        return redirect('articles');
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\View\View
     */
    public function show($id)
    {
        $article = Article::findOrFail($id);
        $article->created_by = User::where('id', $article->created_by)->get()->first()->first_name;
        $article->modified_by = User::where('id', $article->modified_by)->get()->first()->first_name;
        $article->area = Content_Area::where('id', $article->area)->get()->first()->name;

        if ($article->page > 0) {
            $article->page = Page::where('id', $article->page)->get()->first()->name;
        }
        elseif($article->page == -1) {
            $article->page = 'Archived';
        }
        else {
            $article->page = 'All Pages';
        }

        return view('articles.show', compact('article'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\View\View
     */
    public function edit($id)
    {
        $pages = ['null' => 'No Pages(Archive)', '0' => 'All Pages',] + Page::oldest()->lists('name', 'id');
        $content_area = Content_Area::oldest()->lists('name', 'id');
        $article = Article::findOrFail($id);

        return view('articles.edit', compact('article', 'pages', 'content_area'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function update($id, CreateArticleRequest $request)
    {
        $request['modified_by'] = Auth::id();
        $article = Article::findOrFail($id);
        $article->update($request->all());

        return redirect('articles');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function destroy($id)
    {

        $article = Article::findOrFail($id);
        $article->page = -1;
        $article->save();

        return redirect('articles');
    }

}
