@extends('admin')

@section('content')

    <a href="{{ url('/pages/create') }}" class="btn btn-primary btn-lg">Create New Page</a>

    @foreach($pages as $page)
        <article>
            <h2>
                <a href="{{ url('/pages/' . $page->id . '') }}">{{ $page->name }}</a>
            </h2>

            <div class="body"><p>{{ str_limit($page->description, 30, '...') }}</p></div>
        </article>
    @endforeach

@stop