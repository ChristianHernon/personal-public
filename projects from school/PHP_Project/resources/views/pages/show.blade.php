@extends('admin')

@section('content')

    <h1>{{ $page->name }}</h1>
    <hr>

    <article>
        <h3>Description<br>
            <small>{{ $page->description }}</small>
        </h3>
        <h3>Alias<br>
            <small>{{ $page->alias }}</small>
        </h3>
        <h3>Created By<br>
            <small>{{ $page->created_by }} at {{ $page->created_at }}</small>
        </h3>
        <h3>Modified By<br>
            <small>{{ $page->modified_by }} at {{ $page->updated_at }}</small>
        </h3>
    </article>

    <a href="{{ url('pages/' . $page->id . '/edit') }}" class="btn btn-primary btn-lg">Edit</a>

    {!! Form::open(array('url' => 'pages/' . $page->id, 'class' => 'pull-right')) !!}
    {!! Form::hidden('_method', 'DELETE') !!}
    {!! Form::submit('Delete', array('class' => 'btn btn-danger btn-lg')) !!}
    {!! Form::close() !!}

@stop
