@extends('admin')

@section('content')

    <h1>Create a new Article</h1>
    <hr>

    {!! Form::open(['url'=>'articles', 'autocomplete' => 'off']); !!}

    @include ('partials.formArticles', ['submitButtonText' => 'Create Article',
                                        'pages' => $pages,
                                        'content_area' => $content_area])

    {!! Form::close() !!}

    @include ('errors.list')

@stop