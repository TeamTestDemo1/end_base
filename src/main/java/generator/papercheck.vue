<template>
    <div>
        <!--    这是筛选区-->
        <div style="padding: 10px " >
            <el-select v-model="value1" multiple placeholder="请选择发表时间">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>

            <el-select
                    v-model="value2"
                    multiple
                    collapse-tags
                    style="margin-left: 20px;"
                    placeholder="筛选论文类型">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>

            <el-select
                    v-model="value3"
                    multiple
                    collapse-tags
                    style="margin-left: 20px;"
                    placeholder="筛选审核状态">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
            <!--        这是功能区-->
            <el-input v-model="text" @keyup.enter.native="load" placeholder="请输入搜索内容" style="width: 200px;margin-left: 20px"> <i slot="prefix" class="el-input__icon el-icon-search"></i></el-input>
            <el-button @click="im" size="small" type="primary" style="margin-left: 10px ">导入</el-button>
            <el-button @click="out" size="small" type="primary" style="margin-left: 10px ">导出</el-button>
        </div>
        <!--    这是表格区-->
        <el-table
                ref="multipleTable"
                :data="tableData"
                border
                :stripe="true"
                style="width: 100%"
                @selection-change="handleSelectionChange">
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column
                    prop="id"
                    label="序号"
            >
            </el-table-column>
            <el-table-column
                    prop="paperId"
                    label="论文编号"
            >
            </el-table-column>
            <el-table-column
                    prop="paperTitle"
                    label="论文名称"
            >
            </el-table-column>
            <el-table-column
                    prop="authorAll"
                    label="所有作者"
            >
            </el-table-column>
            <el-table-column
                    prop="authorFirst"
                    label="第一作者"
            >
            </el-table-column>
            <el-table-column
                    prop="paperUnit"
                    label="署名单位"
            >
            </el-table-column>
            <el-table-column
                    prop="paperTime"
                    label="发行日期"
            >
            </el-table-column>
            <el-table-column
                    prop="paperDoi"
                    label="doi"
            >
            </el-table-column>
            <el-table-column
                    prop="paperType"
                    label="论文类型"
            >
            </el-table-column>
            <el-table-column
                    prop="state"
                    label="审核状态"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="check"
                    label="审核"
                    width="200"
                    show-overflow-tooltip>
                <template v-slot="scope">
                    <el-button type="success" circle  @click="changeState(scope.row,'通过')" :disabled="scope.row.state !=='待审核'">通过</el-button>
                    <el-button type="danger" circle  @click="changeState(scope.row,'拒绝')" :disabled="scope.row.state !=='待审核'">拒绝</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--这是审核区  待修正-->
        <el-dialog title="审核" :visible.sync="showChkStateDialog">
            <el-form>
                <el-form-item>
                    <el-input
                            type="textarea"
                            style="width:400px"
                            v-model="entity.journistRemark"
                            placeholder="请输入审核意见">
                    </el-input>
                </el-form-item>

                <el-form-item>
                    <el-button @click="showChkStateDialog = false">取消</el-button>
                    <el-button type="primary" @click="showChkStateDialog = false">确定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
        <!--    分页功能-->
        <div style="margin: 15px ">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-sizes="[1,2, 5, 10]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    // import API from '../../utils/request'

    const url = "/api/class/"

    export default {
        name: "papercheck",
        data() {
            return {
                options: [],
                value1: [],
                value2: [],
                value3: [],
                tableData: [{
                    id:'1',
                    paperId:'123456',
                    paperTitle:'你好',
                    authorAll:'喜羊羊、美羊羊',
                    authorFirst:'喜羊羊',
                    paperUnit:'中南大学',
                    paperTime:'2021',
                    paperDoi:'510648453',
                    paperType:'中文',
                    state:'待审核',
                }

                ],
                user: {},
                pageNum: 1,
                pageSize: 10,
                total: 1,
                entity: {},
                dialogFormVisible: false,
                multipleSelection: [],
                text:''

            };
        },
        created() {
            // this.user = sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {}
            this.load()
        },
        methods: {
            changeRole(row) {
                this.entity = JSON.parse(JSON.stringify(row));
                this.save();
            },
            handleSizeChange(pageSize) {
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum) {
                this.pageNum = pageNum
                this.load()
            },
            load() {
                API.get(url + "/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        name: this.text
                    }
                }).then(res => {
                    this.tableData = res.data.records || []
                    this.total = res.data.total
                })

                API.get("/api/role").then(res => {
                    this.options = res.data
                })
            },
            add() {
                this.entity = {}
                this.dialogFormVisible = true
            },
            edit(obj) {
                this.entity = JSON.parse(JSON.stringify(obj))
                this.dialogFormVisible = true
            },
            save() {
                if (!this.entity.id) {
                    API.post(url, this.entity).then(res => {
                        if (res.code === '0') {
                            this.$message({
                                type: "success",
                                message: "操作成功"
                            })
                        } else {
                            this.$message({
                                type: "error",
                                message: res.msg
                            })
                        }
                        this.load()
                        this.dialogFormVisible = false
                    })
                } else {
                    API.put(url, this.entity).then(res => {
                        if (res.code === '0') {
                            this.$message({
                                type: "success",
                                message: "操作成功"
                            })
                        } else {
                            this.$message({
                                type: "error",
                                message: res.msg
                            })
                        }
                        this.load()
                        this.dialogFormVisible = false
                    })
                }
            },
            del(id) {
                API.delete(url + id).then(res => {
                    if (res.code === '0') {
                        this.$message({
                            type: "success",
                            message: "操作成功"
                        })
                    } else {
                        this.$message({
                            type: "error",
                            message: res.msg
                        })
                    }
                    this.load()
                })
            },
            changeState(row,state){
                console.log(row);
                this.form.state=state;
                this.save();

            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            }
        },
    };
</script>

<style scoped>
</style>
