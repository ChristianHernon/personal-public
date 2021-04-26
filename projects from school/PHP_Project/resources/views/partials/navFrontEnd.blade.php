<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
        @foreach($pages as $page)
            <li><a href="{{ url('/site/' . $page->alias) }}">{{ $page->name }}</a></li>
        @endforeach
    </ul>

    <ul class="nav navbar-nav navbar-right">
        <div style="float: left; padding-top: 13px; padding-right: 10px;">
            {!! Form::open(['url'=>'site', 'autocomplete' => 'off']); !!}
            {!! Form::text('search', null, ['class' => '', 'required']) !!}
            {!! Form::submit('Search', ['class' => 'btn btn-primary btn-xs']) !!}
            {!! Form::close() !!}
        </div>

        @if(!Auth::guest())
            @if(Auth::user()->isAdmin() || Auth::user()->isEditor())
                <li><a href="{{ url('/admin') }}">Admin Panel</a></li>
            @endif
        @endif

        @if(!Auth::guest() && Auth::user()->isAuthor())
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                   aria-expanded="false"> Edit/Create Article <span class="glyphicon glyphicon-plus"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="{{ url('/articles/create') }}">Create New Article</a></li>
                    <li><a href="{{ url('/articles') }}">Edit Articles</a></li>
                </ul>
            </li>
        @endif

        @if (Auth::guest())
            <li><a href="{{ url('/auth/login') }}">Login</a></li>
            {{--<li><a href="{{ url('/auth/register') }}">Register</a></li>--}}
        @else
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                   aria-expanded="false">{{ Auth::user()->first_name . " " . Auth::user()->last_name}} <span class="glyphicon glyphicon-user"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="{{ url('/auth/logout') }}">Logout</a></li>
                </ul>
            </li>
        @endif
    </ul>
</div>
