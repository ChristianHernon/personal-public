@extends('admin')

@section('content')

    <h1>Create a new Content Area</h1>
    <hr>

    {!! Form::open(['url'=>'content_area', 'autocomplete' => 'off']); !!}

    @include ('partials.formContentArea', ['submitButtonText' => 'Create Content Area'])

    {!! Form::close() !!}

    @include ('errors.list')

@stop