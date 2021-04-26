@extends('admin')

@section('content')

    <h1>{{ $article->title }}</h1>
    <hr>

    <article>
        <h3>Body<br>
            <small>{{ $article->body }}</small>
        </h3>
        <h3>Page<br>
            <small>{{ $article->page }}</small>
        </h3>
        <h3>Area<br>
            <small>{{ $article->area }}</small>
        </h3>
        <h3>Created By<br>
            <small>{{ $article->created_by }} at {{ $article->created_at }}</small>
        </h3>
        <h3>Modified By<br>
            <small>{{ $article->modified_by }} at {{ $article->updated_at }}</small>
        </h3>
    </article>

    <a href="{{ url('articles/' . $article->id . '/edit') }}" class="btn btn-primary btn-lg">Edit</a>

    {!! Form::open(array('url' => 'articles/' . $article->id, 'class' => 'pull-right')) !!}
    {!! Form::hidden('_method', 'DELETE') !!}
    {!! Form::submit('Delete this Article', array('class' => 'btn btn-danger btn-lg')) !!}
    {!! Form::close() !!}

@stop
