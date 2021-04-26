@extends('admin')

@section('content')

    <h1>Create a new User</h1>
    <hr>

    {!! Form::open(['url'=>'users', 'autocomplete' => 'off']); !!}

    @include ('partials.formUser', ['submitButtonText' => 'Create User', 'permissions' => $permissions,
                                    'activePermissions' => null])

    {!! Form::close() !!}

    @include ('errors.list')

@stop