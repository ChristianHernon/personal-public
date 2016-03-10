<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class CSS_Template extends Model
{

    /**
     * The database table used by the model.
     *
     * @var string
     */
    protected $table = 'cssTemplates';

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'name',
        'description',
        'content',
        'active',
        'created_by',
        'modified_by'
    ];

}
