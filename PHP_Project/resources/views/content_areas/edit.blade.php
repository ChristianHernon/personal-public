@extends('admin')

@section('content')

    <h1>Update Content Area</h1>
    <hr>

    {!! Form::model($content_Area, ['method' => 'PATCH',
    'action' => ['ContentAreaController@update', $content_Area->id],
    'autocomplete' => 'off']); !!}

    @include ('partials.formContentArea', ['submitButtonText' => 'Update Content Area'])

    {!! Form::close() !!}

    @include ('errors.list')

@stop