@extends('admin')

@section('content')

    <h1>Edit: {!! $css->name !!}</h1>
    <hr>

    {!! Form::model($css, ['method' => 'PATCH',
    'action' => ['CssTemplateController@update', $css->id],
    'autocomplete' => 'off']) !!}

    @include ('partials.formCSS', ['submitButtonText' => 'Update CSS Template'])

    {!! Form::close() !!}

    @include ('errors.list')

@stop