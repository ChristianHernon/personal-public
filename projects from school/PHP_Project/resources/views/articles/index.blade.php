@extends('admin')

@section('content')

    <a href="{{ url('/articles/create') }}" class="btn btn-primary btn-lg">Create New Article</a>

    @foreach($articles as $article)
        <article>
            <h2>
                <a href="{{ url('/articles', $article->id) }}">{{ $article->title }}</a>
            </h2>

            <div class="body"><p>{{ str_limit($article->body, 30, '...') }}</p></div>
        </article>
    @endforeach

@stop