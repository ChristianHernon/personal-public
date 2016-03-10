@extends('admin')

@section('content')

    <a href="{{ url('/content_area/create') }}" class="btn btn-primary btn-lg">Create New Content Area</a>

    @foreach($contentAreas as $contentArea)
        <article>
            <h2>
                <a href="{{ url('/content_area/' .  $contentArea->id) }}">{{ $contentArea->name }}</a>
            </h2>

            <div class="body"><p>{{ str_limit($contentArea->description, 20, '...') }}</p></div>
        </article>
    @endforeach

@stop