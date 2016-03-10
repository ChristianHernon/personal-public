@extends('site')

@section('content')
        <section class="row">
            @foreach($pageContent->reverse() as $article)
                @include('partials.siteArea', ['article' => $article])
            @endforeach
        </section>
@stop