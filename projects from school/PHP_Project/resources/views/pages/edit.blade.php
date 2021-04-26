@extends('admin')

@section('content')

    <h1>Edit Page</h1>
    <hr>

    {!! Form::model($page, ['method' => 'PATCH',
    'action' => ['PagesController@update', $page->id],
    'autocomplete' => 'off']); !!}

    @include ('partials.formPages', ['submitButtonText' => 'Update Page'])

    {!! Form::close() !!}

    @include ('errors.list')

@stop