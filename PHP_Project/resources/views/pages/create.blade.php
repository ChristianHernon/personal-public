@extends('admin')

@section('content')

    <h1>Create a new Page</h1>
    <hr>

    {!! Form::open(['url'=>'pages', 'autocomplete' => 'off']); !!}

    @include ('partials.formPages', ['submitButtonText' => 'Create Page'])

    {!! Form::close() !!}

    @include ('errors.list')

@stop