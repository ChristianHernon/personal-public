@extends('admin')

@section('content')

    <h1>{{ $css->name }}</h1>
    <hr>

    <article>
        <h3>Description<br>
            <small>{{ $css->description }}</small>
        </h3>
        <h3>Content<br>
            <small>{{ $css->content }}</small>
        </h3>
        <h3>Status<br>
            @if($css->active == '1')
                <small>Enabled</small>
            @else
                <small>Disabled</small>
            @endif
        </h3>
        <h3>Created By<br>
            <small>{{ $css->created_by }} at {{ $css->created_at }}</small>
        </h3>
        <h3>Modified By<br>
            <small>{{ $css->modified_by }} at {{ $css->updated_at }}</small>
        </h3>
    </article>

    <a href="{{ url('css_template/' . $css->id . '/edit') }}" class="btn btn-primary btn-lg">Edit</a>

    {!! Form::open(array('url' => 'css_template/' . $css->id, 'class' => 'pull-right')) !!}
    {!! Form::hidden('_method', 'DELETE') !!}
    {!! Form::submit('Delete', array('class' => 'btn btn-danger btn-lg')) !!}
    {!! Form::close() !!}

@stop
