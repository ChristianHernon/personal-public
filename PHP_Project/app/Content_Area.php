<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class Content_Area extends Model
{

    /**
     * The database table used by the model.
     *
     * @var string
     */
    protected $table = 'contentAreas';

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'name',
        'orderBy',
        'description',
        'created_by',
        'modified_by'
    ];

}
