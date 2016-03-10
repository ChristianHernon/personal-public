<?php namespace App\Http\Controllers;

use App\Http\Requests;
use App\Http\Requests\CreateUserRequest;
use App\Permission;
use App\Permission_User;
use App\User;
use Auth;

class UserController extends Controller
{

    /**
     *
     */
    public function __construct()
    {
        $this->middleware('admin');
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\View\View
     */
    public function index()
    {
        $users = User::all();

        return view('users.index', compact('users'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        $permissions = Permission::lists('permission_name', 'id');

        return view('users.create', compact('permissions'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @return \Illuminate\Http\RedirectResponse
     */
    public function store(CreateUserRequest $request)
    {
        $request['modified_by'] = Auth::id();
        $request['password'] = bcrypt($request->input('password'));
        Auth::user()->users()->save(new User($request->all()));
        $permissions = $request->get('permissions');
        if ($permissions != null) {
            foreach ($permissions as $permission) {
                Permission_User::create(['user_id' => User::all()->last()->id, 'permission_id' => $permission]);
            }
        }

        return redirect('users');
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\View\View
     */
    public function show($id)
    {
        $user = User::findOrFail($id);

        $user->created_by = User::where('id', $user->created_by)->get()->first()->first_name;
        $user->modified_by = User::where('id', $user->modified_by)->get()->first()->first_name;

        return view('users.show', compact('user'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\View\View
     */
    public function edit($id)
    {
        $permissions = Permission::lists('permission_name', 'id');
        $user = User::findOrFail($id);
        $activePermissions = Permission_User::where('user_id', $id)->lists('permission_id');

        return view('users.edit', compact('user', 'permissions', 'activePermissions'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\RedirectResponse
     */
    public function update($id, CreateUserRequest $request)
    {
        $request['modified_by'] = Auth::id();
        $request['password'] = bcrypt($request->input('password'));
        $permissions = $request->get('permissions');
        Permission_User::where('user_id', $id)->delete();
        foreach ($permissions as $permission) {
            Permission_User::create([
                'user_id' => $id,
                'permission_id' => $permission,
            ]);
        }
        $user = User::findOrFail($id);
        $user->update($request->all());

        return redirect('users');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\RedirectResponse
     */
    public function destroy($id)
    {
        $user = User::findOrFail($id);
        $user->delete();

        return redirect('users');
    }

}
