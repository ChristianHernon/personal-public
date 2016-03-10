<?php namespace App;

use Illuminate\Auth\Authenticatable;
use Illuminate\Auth\Passwords\CanResetPassword;
use Illuminate\Contracts\Auth\Authenticatable as AuthenticatableContract;
use Illuminate\Contracts\Auth\CanResetPassword as CanResetPasswordContract;
use Illuminate\Database\Eloquent\Model;

class User extends Model implements AuthenticatableContract, CanResetPasswordContract
{

    use Authenticatable, CanResetPassword;

    /**
     * The database table used by the model.
     *
     * @var string
     */
    protected $table = 'users';

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'first_name',
        'last_name',
        'email',
        'password',
        'created_by',
        'modified_by',
    ];

    /**
     * The attributes excluded from the model's JSON form.
     *
     * @var array
     */
    protected $hidden = ['password', 'remember_token'];

    public function articles()
    {
        return $this->hasMany('App\Article', 'created_by');
    }

    public function pages()
    {
        return $this->hasMany('App\Page', 'created_by');
    }

    public function users()
    {
        return $this->hasMany('App\User', 'created_by');
    }

    public function templates()
    {
        return $this->hasMany('App\CSS_Template', 'created_by');
    }

    public function areas()
    {
        return $this->hasMany('App\Content_Area', 'created_by');
    }

    public function permissions()
    {
        return $this->belongsToMany('App\Permission');
    }

    public function isAdmin()
    {
        foreach ($this->permissions()->get() as $role) {
            if ($role->permission_name == 'Administrator') {
                return true;
            }
        }
        return false;
    }

    public function isEditor()
    {
        foreach ($this->permissions()->get() as $role) {
            if ($role->permission_name == 'Editor') {
                return true;
            }
        }
        return false;
    }

    public function isAuthor()
    {
        foreach ($this->permissions()->get() as $role) {
            if ($role->permission_name == 'Author') {
                return true;
            }
        }
        return false;
    }

}
