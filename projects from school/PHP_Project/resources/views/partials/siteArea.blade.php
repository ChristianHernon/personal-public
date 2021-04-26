<article class="">
    @if (!Auth::guest() && Auth::user()->isAuthor())
        <h3><a href="{{ url('articles/'. $article->id) }}">{{ $article->title }}</a></h3>
    @else
        <h3>{{ $article->title }}</h3>
    @endif
    {!! $article->body !!}
</article>
