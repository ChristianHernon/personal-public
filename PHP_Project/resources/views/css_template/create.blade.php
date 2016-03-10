@extends('admin')

@section('content')

    <h1>Create a new CSS Template</h1>
    <hr>

    {!! Form::open(['url'=>'css_template', 'autocomplete' => 'off']); !!}

    @include ('partials.formCSS', ['submitButtonText' => 'Create CSS'])

    {!! Form::close() !!}

    @include ('errors.list')

@stop