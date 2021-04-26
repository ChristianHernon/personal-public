<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
        <li><a href="{{ url('/') }}">Home</a></li>
    </ul>

    <ul class="nav navbar-nav navbar-right">
        @if(Auth::user()->isAdmin())
            <li><a href="{{ url('/admin/users') }}">Users</a></li>
        @endif

        @if(Auth::user()->isEditor())
            <li><a href="{{ url('/admin/pages') }}">Pages</a></li>
            <li><a href="{{ url('/admin/articles') }}">Articles</a></li>
            <li><a href="{{ url('/admin/css_templates') }}">CSS Templates</a></li>
            <li><a href="{{ url('/admin/content_areas') }}">Content Areas</a></li>
        @endif
            
        @if (Auth::guest())
            <li><a href="{{ url('/auth/login') }}">Login</a></li>
            <li><a href="{{ url('/auth/register') }}">Register</a></li>
        @else
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                   aria-expanded="false">{{ Auth::user()->name }} <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="{{ url('/auth/logout') }}">Logout</a></li>
                </ul>
            </li>
        @endif
    </ul>
</div>
