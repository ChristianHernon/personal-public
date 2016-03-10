@extends('admin')

@section('content')

    <a href="{{ url('/css_template/create') }}" class="btn btn-primary btn-lg">Create New CSS</a>

    @foreach($css_templates as $css_template)
        <article>
            <h2>
                <a href="{{ url('/css_template', $css_template->id) }}">{{ $css_template->name }}</a>
            </h2>

            <div class="body"><p>{{ str_limit($css_template->description, 30, '...') }}</p></div>
        </article>
    @endforeach

@stop