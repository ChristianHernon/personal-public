@extends('admin')

@section('content')

    <h1>Edit: {!! $article->title !!}</h1>
    <hr>

    {!! Form::model($article, ['method' => 'PATCH',
    'action' => ['ArticlesController@update', $article->id],
    'autocomplete' => 'off']) !!}

    @include ('partials.formArticles', ['submitButtonText' => 'Edit Article',
                                        'pages' => $pages,
                                        'content_area' => $content_area])

    {!! Form::close() !!}

    @include ('errors.list')

@stop