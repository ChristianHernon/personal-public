<div class="form-group">
    {!! Form::label('first_name', 'First Name:') !!}
    {!! Form::text('first_name', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('last_name', 'Last Name:') !!}
    {!! Form::text('last_name', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('email', 'Email:') !!}
    {!! Form::text('email', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('password', 'Password:') !!}
    {!! Form::password('password', array('placeholder'=>'Password', 'class'=>'form-control' ) ) !!}
</div>

<div class="form-group">
    {!! Form::label('permissions', 'Permissions:') !!}
    {!! Form::select('permissions[]', $permissions, $activePermissions,
    ['class' => 'form-control', 'id' => 'permissions', 'multiple']) !!}
</div>

<div class="form-group">
    {!! Form::submit($submitButtonText, ['class' => 'btn btn-primary btn-lg']) !!}
</div>

@section('footer')
    <script type="text/javascript">
        $('#permissions').select2();
    </script>
@endsection