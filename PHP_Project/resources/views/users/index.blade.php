@extends('admin')

@section('content')

    <a href="{{ url('/users/create') }}" class="btn btn-primary btn-lg">Create New User</a>

    @foreach($users as $user)
        <article>
            <h2><a href="{{ url('/users/' . $user->id . '') }}">{{ $user->first_name }}</a></h2>

            <div class="body">
                <p>{{ str_limit($user->last_name, 30, '...') }}</p>
            </div>
        </article>
    @endforeach

@stop