@extends('admin')

@section('content')

    <h1>Edit User</h1>
    <hr>

    {!! Form::model($user, ['method' => 'PATCH',
    'action' => ['UserController@update', $user->id],
    'autocomplete' => 'off']); !!}

    @include ('partials.formUser', ['submitButtonText' => 'Update User', 'permissions' => $permissions,
                                    'activePermissions' => $activePermissions])

    {!! Form::close() !!}

    @include ('errors.list')

@stop