@extends('admin')

@section('content')

    <h1>{{ $content_Area->name }}</h1>
    <hr>

    <article>
        <h3>Description<br>
            <small>{{ $content_Area->description }}</small>
        </h3>
        <h3>Order By<br>
            <small>{{ $content_Area->orderBy }}</small>
        </h3>
        <h3>Created By<br>
            <small>{{ $content_Area->created_by }} at {{ $content_Area->created_at }}</small>
        </h3>
        <h3>Modified By<br>
            <small>{{ $content_Area->modified_by }} at {{ $content_Area->updated_at }}</small>
        </h3>
    </article>

    <a href="{{ url('content_area/' . $content_Area->id . '/edit') }}" class="btn btn-primary btn-lg">Edit</a>

    {!! Form::open(array('url' => 'content_area/' . $content_Area->id, 'class' => 'pull-right')) !!}
    {!! Form::hidden('_method', 'DELETE') !!}
    {!! Form::submit('Delete', array('class' => 'btn btn-danger btn-lg')) !!}
    {!! Form::close() !!}

@stop
