@extends('admin')

@section('content')

    <h1>{{ $user->first_name . " " . $user->last_name}}</h1>
    <hr>

    <article>
        <h3>Email<br>
            <small>{{ $user->email }}</small>
        </h3>
        <h3>Created By<br>
            <small>{{ $user->created_by }} at {{ $user->created_at }}</small>
        </h3>
        <h3>Modified By<br>
            <small>{{ $user->modified_by }} at {{ $user->updated_at }}</small>
        </h3>
    </article>

    @if (Auth::user()->id != $user->id)
        <a href="{{ url('users/' . $user->id . '/edit') }}" class="btn btn-primary btn-lg">Edit</a>
        {!! Form::open(array('url' => 'users/' . $user->id, 'class' => 'pull-right')) !!}
        {!! Form::hidden('_method', 'DELETE') !!}
        {!! Form::submit('Delete', array('class' => 'btn btn-danger btn-lg')) !!}
        {!! Form::close() !!}
    @else
        <h3>Logged In User Cannot Be Modified</h3>
    @endif

@stop
