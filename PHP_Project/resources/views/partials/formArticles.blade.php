<div class="form-group">
    {!! Form::label('title', 'Title:') !!}
    {!! Form::text('title', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('page', 'Page: ') !!}
    {!! Form::select('page', $pages, null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('area', 'Content Area: ') !!}
    {!! Form::select('area', $content_area, null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('body', 'Body: ') !!}
    {!! Form::textarea('body', null, ['class' => 'form-control', 'id' => 'tiny']) !!}
</div>

<div class="form-group">
    {!! Form::submit($submitButtonText, ['class' => 'btn btn-primary btn-lg']) !!}
</div>

@section('footer')
    <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
    <script>
        tinymce.init({
            selector: 'textarea#tiny',
            setup: function (ed) {
                ed.on('init', function () {
//                    this.execCommand("fontName", false, "helvetica");
//                    this.execCommand("fontSize", false, "1.75em");
                });
            }
        });
    </script>
@endsection